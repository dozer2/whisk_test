package com.borisenko.test.util.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 20.04.18.
 */
public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<IElement> clazz;

    public LocatingCustomElementListHandler(ElementLocator locator,
            Class<IElement> clazz) {
        this.locator = locator;
        this.clazz = clazz;
    }

    public Object invoke(Object object, Method method,
            Object[] objects) throws Throwable {
        // Находит список WebElement и обрабатывает каждый его элемент,
        // возвращает новый список с элементами кастомного класса
        List<WebElement> elements = locator.findElements();
        List<IElement> customs = new ArrayList<IElement>();

        for (WebElement element : elements) {
           IElement elem=WrapperFactory.createInstance(clazz, element);
            PageFactory.initElements(new CustomFieldDecorator(element), elem);
            customs.add(elem);

        }
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}

