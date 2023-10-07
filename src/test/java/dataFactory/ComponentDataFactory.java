package dataFactory;

import pojo.ComponentPojo;
import pojo.ProductPojo;

import java.util.ArrayList;
import java.util.List;

public class ComponentDataFactory {
    public static ProductPojo createComponentStandard(ProductPojo produto) {
        List<ComponentPojo> components = new ArrayList<>();
        ComponentPojo component1 = new ComponentPojo("Standard Component 1", 2);
        components.add(component1);
        ComponentPojo component2 = new ComponentPojo("Standard Component 2", 4);
        components.add(component2);
        produto.setComponentes(components);
        return produto;
    }

    public static ComponentPojo addOneComponent(String componentName, int componentsQuantity) {
        ProductPojo product = ProductPojo.getInstance();
        List<ComponentPojo> components = new ArrayList<>();
        ComponentPojo component = new ComponentPojo(componentName, componentsQuantity);
        components.add(component);
        product.setComponentes(components);
        return component;
    }

}
