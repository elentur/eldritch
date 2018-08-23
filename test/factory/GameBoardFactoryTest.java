package factory;


import enums.FieldID;
import model.Field;
import model.GameBoard;
import model.Item.assets.AighteenthDerringer;
import model.Neighbour;
import org.junit.Assert;
import org.testng.annotations.Test;


public class GameBoardFactoryTest {
    @Test
    public void testGetGameBoard() throws Exception {
        GameBoard gameBoard = GameBoardFactory.getGameBoard();
        Assert.assertNotNull(gameBoard);
        Assert.assertTrue(gameBoard.getFields().size() == 36);
        Field field = gameBoard.getField(FieldID.SAN_FRANCISCO);
        Assert.assertNotNull(field);
        Assert.assertTrue(field.getNeighbours().size() == 5);
        Field neighbour = field.getNeighbours().get(0).getField();
        boolean isBidirectional=false;
        for(Neighbour n: neighbour.getNeighbours()){
            if(n.getField().equals(field)){
                isBidirectional=true;
                break;
            }
        }
        Assert.assertTrue(isBidirectional);



    }

}