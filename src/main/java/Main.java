import dal.*;
import dto.*;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args){

        try {
            DeleteDAO.delete();
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        System.out.printf("Test af at databasen returnerer det vi sender til den:\n");


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

        try {
            userDAO.createUser(userDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        try {
            if (userDAO.getUser(userDTO.getUserId()).toString().equals(userDTO.toString()))  {
                System.out.printf("%1$-24s", "Bruger:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Bruger:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        IIngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setActive(true);
        ingredientDTO.setIngredientId(222);
        ingredientDTO.setIngredientName("bob løg");
        ingredientDTO.setMargin(5.2);

        try {
            ingredientDAO.createIngredient(ingredientDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        try {
            IIngredientDTO in = ingredientDAO.getIngredient(ingredientDTO.getIngredientId());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        try {
            if (ingredientDAO.getIngredient(ingredientDTO.getIngredientId()).toString().equals(ingredientDTO.toString())){
                System.out.printf("%1$-24s", "Ingrediens:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Ingrediens:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        IProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(321);
        productDTO.setProductName("Bob og løgsuppe");

        try {
            productDAO.createProduct(productDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        try {
            if (productDAO.getProduct(productDTO.getProductId()).toString().equals(productDTO.toString()))  {
                System.out.printf("%1$-24s", "Produkt:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Produkt:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
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

        try {
            recipeDAO.createRecipe(recipeDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        try {
            if (recipeDAO.getRecipe(recipeDTO.getRecipeId()).toString().equals(recipeDTO.toString()))  {
                System.out.printf("%1$-24s", "Opskrift:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Opskrift:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        IMaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setMaterialBatchId(987);
        materialDTO.setIngredientId(222);
        materialDTO.setAmount(10000.00);
        materialDTO.setDate(new Date(100000000));
        materialDTO.setOrder(false);
        materialDTO.setUserId(123);

        try {
            materialDAO.createMaterial(materialDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        try {
            if (materialDAO.getMaterial(materialDTO.getMaterialBatchId()).toString().equals(materialDTO.toString()))  {
                System.out.printf("%1$-24s", "Råvare:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Råvare:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
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

        try {
            prodBatchDAO.createProdBatch(prodBatchDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        try {
            if (prodBatchDAO.getProdBatch(prodBatchDTO.getProdBatchId()).toString().equals(prodBatchDTO.toString()))  {
                System.out.printf("%1$-24s", "Prod Batch:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Prod Batch:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }

        System.out.printf("\nEfter alt er oprettet:\n");

        try {
            if (userDAO.getUser(userDTO.getUserId()).toString().equals(userDTO.toString()))  {
                System.out.printf("%1$-24s", "Bruger:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Bruger:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        try {
            if (ingredientDAO.getIngredient(ingredientDTO.getIngredientId()).toString().equals(ingredientDTO.toString())){
                System.out.printf("%1$-24s", "Ingrediens:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Ingrediens:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        try {
            if (productDAO.getProduct(productDTO.getProductId()).toString().equals(productDTO.toString()))  {
                System.out.printf("%1$-24s", "Produkt:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Produkt:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        try {
            if (recipeDAO.getRecipe(recipeDTO.getRecipeId()).toString().equals(recipeDTO.toString()))  {
                System.out.printf("%1$-24s", "Opskrift:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Opskrift:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        materialDTO.setAmount(materialDTO.getAmount()-recipeDTO.getAmount().get(0));
        try {
            if (materialDAO.getMaterial(materialDTO.getMaterialBatchId()).toString().equals(materialDTO.toString()))  {
                System.out.printf("%1$-24s", "Råvare:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Råvare:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        try {
            if (prodBatchDAO.getProdBatch(prodBatchDTO.getProdBatchId()).toString().equals(prodBatchDTO.toString()))  {
                System.out.printf("%1$-24s", "Prod Batch:");
                System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
            } else {
                System.out.printf("%1$-24s", "Prod Batch:");
                System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }


        System.out.printf("\nTest af bruger uden rettigheder kaster fejl:\n");

        IUserDTO noRightsUserDTO = new UserDTO();
        noRightsUserDTO.setUserId(111);
        noRightsUserDTO.setUserName("Useless");
        noRightsUserDTO.setAdmin(false);
        noRightsUserDTO.setLabo(false);
        noRightsUserDTO.setPharma(false);
        noRightsUserDTO.setPLeader(false);

        try {
            userDAO.createUser(noRightsUserDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }


        IRecipeDTO noRightsRecipeDTO = new RecipeDTO();
        noRightsRecipeDTO.setRecipeId(1234);
        noRightsRecipeDTO.setProductId(321);
        noRightsRecipeDTO.setDate(new Date(100000000));
        noRightsRecipeDTO.setPharmaList(new ArrayList<>());
        noRightsRecipeDTO.getPharmaList().add(111);
        noRightsRecipeDTO.setIngList(new ArrayList<>());
        noRightsRecipeDTO.getIngList().add(222);
        noRightsRecipeDTO.setAmount(new ArrayList<>());
        noRightsRecipeDTO.getAmount().add(123.21);

        try {
            recipeDAO.createRecipe(noRightsRecipeDTO);
            System.out.printf("%1$-24s", "Opskrift:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Opskrift:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }

        IMaterialDTO noRightsMaterialDTO = new MaterialDTO();
        noRightsMaterialDTO.setMaterialBatchId(989);
        noRightsMaterialDTO.setIngredientId(222);
        noRightsMaterialDTO.setAmount(10000.00);
        noRightsMaterialDTO.setDate(new Date(1000000000));
        noRightsMaterialDTO.setOrder(false);
        noRightsMaterialDTO.setUserId(111);

        try {
            materialDAO.createMaterial(noRightsMaterialDTO);
            System.out.printf("%1$-24s", "Råvare:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Råvare:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }

        IProdBatchDTO noRightsProdBatchDTO = new ProdBatchDTO();
        noRightsProdBatchDTO.setProdBatchId(657);
        noRightsProdBatchDTO.setLabList(new ArrayList<>());
        noRightsProdBatchDTO.getLabList().add(123);
        noRightsProdBatchDTO.setMatList(new ArrayList<>());
        noRightsProdBatchDTO.getMatList().add(987);
        noRightsProdBatchDTO.setDate(new Date(100000000));
        noRightsProdBatchDTO.setRecipeId(432);
        noRightsProdBatchDTO.setUserId(111);


        try {
            prodBatchDAO.createProdBatch(noRightsProdBatchDTO);
            System.out.printf("%1$-24s", "Prod Batch P-Leder:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Prod Batch P-Leder:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }


        noRightsProdBatchDTO = new ProdBatchDTO();
        noRightsProdBatchDTO.setProdBatchId(657);
        noRightsProdBatchDTO.setLabList(new ArrayList<>());
        noRightsProdBatchDTO.getLabList().add(111);
        noRightsProdBatchDTO.setMatList(new ArrayList<>());
        noRightsProdBatchDTO.getMatList().add(987);
        noRightsProdBatchDTO.setDate(new Date(100000000));
        noRightsProdBatchDTO.setRecipeId(432);
        noRightsProdBatchDTO.setUserId(123);


        try {
            prodBatchDAO.createProdBatch(noRightsProdBatchDTO);
            System.out.printf("%1$-24s", "Prod Batch Laborant:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Prod Batch Laborant:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }

        System.out.printf("\nTest af at duplikater kaster fejl\n");



        try {
            userDAO.createUser(userDTO);
            System.out.printf("%1$-24s", "Bruger:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Bruger:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }

        try {
            ingredientDAO.createIngredient(ingredientDTO);
            System.out.printf("%1$-24s", "Ingrediens:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Ingrediens:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }
        try {
            productDAO.createProduct(productDTO);
            System.out.printf("%1$-24s", "Produkt:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Produkt:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }
        try {
            recipeDAO.createRecipe(recipeDTO);
            System.out.printf("%1$-24s", "Opskrift:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Opskrift:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }

        try {
            materialDAO.createMaterial(materialDTO);
            System.out.printf("%1$-24s", "Råvare:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Råvare:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }

        try {
            prodBatchDAO.createProdBatch(prodBatchDTO);
            System.out.printf("%1$-24s", "Prod Batch:");
            System.out.printf(ANSI_RED + "Virker ikke\n" + ANSI_RESET);
        } catch (IDALException.DALException e){
            System.out.printf("%1$-24s", "Prod Batch:");
            System.out.printf(ANSI_GREEN + "Virker\n" + ANSI_RESET);
        }

    }
}
