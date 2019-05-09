import dal.*;
import dto.*;

import java.sql.Date;

public class Main {

    public static void main(String[] args) throws IDALException.DALException {

        IUserDAO userDAO = new UserDAO();
        IMaterialDAO materialDAO = new MaterialDAO();
        IProductDAO productDAO = new ProductDAO();
        IRecipeDAO recipeDAO = new RecipeDAO();
        IIngredientDAO ingredientDAO = new IngredientDAO();


        IUserDTO userDTO = new UserDTO();
        userDTO.setUserId(123);
        userDTO.setUserName("TEST");
        userDTO.setAdmin(true);
        userDTO.setLabo(true);
        userDTO.setPharma(true);
        userDTO.setPLeader(true);

        userDAO.createUser(userDTO);
        if (userDAO.getUser(userDTO.getUserId()).toString().equals(userDTO.toString()))  {
            System.out.println("bruger virker");
        } else {
            System.out.println("bruger virker ikke");
        }

        IIngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setActive(true);
        ingredientDTO.setIngredientId(222);
        ingredientDTO.setIngredientName("bob løg");
        ingredientDTO.setMargin(5.2);

        ingredientDAO.createIngredient(ingredientDTO);

        IIngredientDTO in = ingredientDAO.getIngredient(ingredientDTO.getIngredientId());

        if (in.getActive()==ingredientDTO.getActive() || in.getIngredientName().equals(ingredientDTO) || in.getMargin() == ingredientDTO.getMargin()){
            System.out.println("ingradiens virker");
        } else {
            System.out.println("ingradiens virker ikke");
        }










    }
}
