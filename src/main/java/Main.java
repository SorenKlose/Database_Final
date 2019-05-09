import dal.*;
import dto.*;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws IDALException.DALException {

        DeleteDAO.delete();


        IUserDAO userDAO = new UserDAO();
        IMaterialDAO materialDAO = new MaterialDAO();
        IProductDAO productDAO = new ProductDAO();
        IRecipeDAO recipeDAO = new RecipeDAO();
        IIngredientDAO ingredientDAO = new IngredientDAO();
        IProdBatchDAO prodBatchDAO = new ProdBatchDAO();


        IUserDTO userDTO = new UserDTO();
        userDTO.setUserId(123);
        userDTO.setUserName("TEST");
        userDTO.setAdmin(true);
        userDTO.setLabo(true);
        userDTO.setPharma(true);
        userDTO.setPLeader(true);

        userDAO.createUser(userDTO);

        if (userDAO.getUser(userDTO.getUserId()).toString().equals(userDTO.toString()))  {
            System.out.printf("%1$-15s", "Bruger:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Bruger:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }

        IIngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setActive(true);
        ingredientDTO.setIngredientId(222);
        ingredientDTO.setIngredientName("bob løg");
        ingredientDTO.setMargin(5.2);

        ingredientDAO.createIngredient(ingredientDTO);

        IIngredientDTO in = ingredientDAO.getIngredient(ingredientDTO.getIngredientId());

        if (ingredientDAO.getIngredient(ingredientDTO.getIngredientId()).toString().equals(ingredientDTO.toString())){
            System.out.printf("%1$-15s", "Ingrediens:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Ingrediens:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }

        IProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(321);
        productDTO.setProductName("Bob og løgsuppe");

        productDAO.createProduct(productDTO);

        if (productDAO.getProduct(productDTO.getProductId()).toString().equals(productDTO.toString()))  {
            System.out.printf("%1$-15s", "Produkt:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Produkt:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }

        IRecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(432);
        recipeDTO.setProductId(321);
        recipeDTO.setDate(new Date(100000000));
        recipeDTO.setPharmaList(new ArrayList<>());
        recipeDTO.getPharmaList().add(123);
        recipeDTO.setIngList(new ArrayList<>());
        recipeDTO.getIngList().add(222);
        recipeDTO.setAmount(new ArrayList<>());
        recipeDTO.getAmount().add(123.21);

        recipeDAO.createRecipe(recipeDTO);

        if (recipeDAO.getRecipe(recipeDTO.getRecipeId()).toString().equals(recipeDTO.toString()))  {
            System.out.printf("%1$-15s", "Opskrift:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Opskrift:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }

        IMaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setMaterialBatchId(987);
        materialDTO.setIngredientId(222);
        materialDTO.setAmount(10000);
        materialDTO.setDate(new Date(100000000));
        materialDTO.setOrder(false);
        materialDTO.setUserId(123);

        materialDAO.createMaterial(materialDTO);

        if (materialDAO.getMaterial(materialDTO.getMaterialBatchId()).toString().equals(materialDTO.toString()))  {
            System.out.printf("%1$-15s", "Råvare:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Råvare:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }

        IProdBatchDTO prodBatchDTO = new ProdBatchDTO();
        prodBatchDTO.setProdBatchId(654);
        prodBatchDTO.setLabList(new ArrayList<>());
        prodBatchDTO.getLabList().add(123);
        prodBatchDTO.setMatList(new ArrayList<>());
        prodBatchDTO.getMatList().add(987);
        prodBatchDTO.setDate(new Date(100000000));
        prodBatchDTO.setRecipeId(432);
        prodBatchDTO.setUserId(123);

        prodBatchDAO.createProdBatch(prodBatchDTO);

        if (prodBatchDAO.getProdBatch(prodBatchDTO.getProdBatchId()).toString().equals(prodBatchDTO.toString()))  {
            System.out.printf("%1$-15s", "Prod Batch:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Prod Batch:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }

        System.out.printf("Efter alt er oprettet:\n");

        if (userDAO.getUser(userDTO.getUserId()).toString().equals(userDTO.toString()))  {
            System.out.printf("%1$-15s", "Bruger:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Bruger:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }
        if (ingredientDAO.getIngredient(ingredientDTO.getIngredientId()).toString().equals(ingredientDTO.toString())){
            System.out.printf("%1$-15s", "Ingrediens:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Ingrediens:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }
        if (productDAO.getProduct(productDTO.getProductId()).toString().equals(productDTO.toString()))  {
            System.out.printf("%1$-15s", "Produkt:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Produkt:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }
        if (recipeDAO.getRecipe(recipeDTO.getRecipeId()).toString().equals(recipeDTO.toString()))  {
            System.out.printf("%1$-15s", "Opskrift:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Opskrift:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }
        if (materialDAO.getMaterial(materialDTO.getMaterialBatchId()).toString().equals(materialDTO.toString()))  {
            System.out.printf("%1$-15s", "Råvare:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Råvare:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }
        if (prodBatchDAO.getProdBatch(prodBatchDTO.getProdBatchId()).toString().equals(prodBatchDTO.toString()))  {
            System.out.printf("%1$-15s", "Prod Batch:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        } else {
            System.out.printf("%1$-15s", "Prod Batch:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        }

    }
}
