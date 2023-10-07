package dataFactory;

import pojo.ProductPojo;

import java.util.ArrayList;
import java.util.List;

public class ProductDataFactory {
    public static ProductPojo createProductWithoutComponent(String productName) {
        ProductPojo product = ProductPojo.getInstance();
        product.setProdutoNome(productName);
        product.setProdutoValor(1000.00);
        List<String> colors = new ArrayList<>();
        colors.add("Standard Black");
        colors.add("Standard White");
        product.setProdutoCores(colors);
        product.setProdutoUrlMock("");
        return product;
    }

    public static ProductPojo createProductWithValueEqualsTo(String productName, double value) {
        ProductPojo product = ProductPojo.getInstance();
        product.setProdutoNome(productName);
        product.setProdutoValor(value);
        List<String> colors = new ArrayList<>();
        colors.add("Standard Red");
        colors.add("Standard Pink");
        product.setProdutoCores(colors);
        product.setProdutoUrlMock("");
        ComponentDataFactory.createComponentStandard(product);
        return product;
    }

    public static ProductPojo updateProductWithValueEqualsTo(String productName, double value) {
        ProductPojo product = ProductPojo.getInstance();
        product.setProdutoNome(productName);
        product.setProdutoValor(value);
        List<String> colors = new ArrayList<>();
        colors.add("Updated Blue");
        colors.add("Updated Yellow");
        product.setProdutoCores(colors);
        product.setProdutoUrlMock("");
        ComponentDataFactory.createComponentStandard(product);
        return product;
    }

}
