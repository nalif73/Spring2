//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.10.04 at 03:17:34 PM MSK 
//


package com.geekbrains.ru.springproduct.soap.categories;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.andfil.spring.ws.categories package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.andfil.spring.ws.categories
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCategoryByNameRequest }
     * 
     */
    public GetCategoryByNameRequest createGetCategoryByNameRequest() {
        return new GetCategoryByNameRequest();
    }

    /**
     * Create an instance of {@link GetCategoryByNameResponse }
     * 
     */
    public GetCategoryByNameResponse createGetCategoryByNameResponse() {
        return new GetCategoryByNameResponse();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

}
