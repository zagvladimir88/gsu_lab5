package org.example;

import org.example.dao.ParameterDAO;
import org.example.dao.ProductDAO;
import org.example.model.Parameters;
import org.example.model.Products;
import org.hibernate.Session;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        ProductDAO productDAO = new ProductDAO(session);
        ParameterDAO parameterDAO = new ParameterDAO(session);

        List<Products> productsList = productDAO.getAllProducts();
        System.out.println("Products:");
        for (Products product : productsList) {
            System.out.println(product.getProductName() + " - " + product.getDescription());
        }
        System.out.println();

        List<Parameters> parametersList = parameterDAO.getAllParameters();
        System.out.println("\nParameters:");
        for (Parameters parameter : parametersList) {
            System.out.println(parameter.getParamName() + " - " + parameter.getParamValue() + " " + parameter.getUnit());
        }
        System.out.println();

        System.out.println("•\tВывести перечень параметров для заданной группы продукции.");
        Products productById = productDAO.getProductById(1L);
        for (Parameters parameters : parametersList) {
            System.out.println(parameters.getParamName() + " " + parameters.getParamGroup() + " " + parameters.getParamValue() + " " + parameters.getUnit());
        }
        System.out.println();


        Parameters parameterById = parameterDAO.getParameterById(1L);
        System.out.println("•\tВывести перечень продукции, не содержащий заданного параметра: " + parameterById.getParamName());
        List<Products> allProducts = productDAO.getAllProducts();
        allProducts.stream().filter(products -> !products.getParameters().contains(parameterById)).forEach(System.out::println);
        System.out.println();


        System.out.println("•\tВывести информацию о продукции для группы телефоны.");
        List<Products> allProducts1 = productDAO.getAllProducts();
        allProducts1.stream().filter(products -> products.getProductGroup().equals("Телефоны")).forEach(System.out::println);
        System.out.println();

        System.out.println("•\tВывести информацию о продукции и всех ее параметрах со значениями.");
        List<Products> allProducts2 = productDAO.getAllProducts();
        for (Products products : allProducts2) {
            System.out.println(products);
            for (Parameters parameters : products.getParameters()) {
                System.out.println(parameters);
            }
        }
        System.out.println("•\tУдалить из базы продукцию, содержащую заданные параметры.");

        System.out.println();
        Parameters parameterById1 = parameterDAO.getParameterById(1L);
        productDAO.getAllProducts()
                .stream()
                .filter(products -> products.getParameters()
                        .contains(parameterById1))
                .forEach(productDAO::deleteProduct);

        System.out.println(productDAO.getAllProducts());


        session.close();

        HibernateUtil.shutdown();
    }
}
