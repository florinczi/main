package florinczi.projects.chessgame.pieces;
import static florinczi.projects.chessgame.pieces.SpecialMoves.CAPTURE;
import java.util.ArrayList;
import java.util.List;
import florinczi.projects.chessgame.Coordinates;
import florinczi.projects.chessgame.Engine;
import florinczi.projects.chessgame.MoveCandidate;
import florinczi.projects.chessgame.Vector;

public class King extends Piece{

    public King(PlayerColor player, Coordinates location, Engine engine) {
        super(player, engine); //adding color and engine ref

        if (player == PlayerColor.BLACK) super.setShortType('k');
        else super.setShortType('K'); // setting "visuals"

        super.setLocation(new Coordinates(location)); //setting inner coords
        engine.getMainBoard().putPiece(this, location); //setting on the hashmap
        newLocation = new Coordinates(location); //init move-probing location
        possibleMoves = new ArrayList<MoveCandidate>(1); //init move list
          
    }

    private Coordinates newLocation;
    
    @Override
    public List<MoveCandidate> checkPossibleMoves() {
        possibleMoves.clear();
        setActiveBoard(getEngine().getMainBoard());
        checkKingMove();
        return possibleMoves;
    }

    public void checkKingMove(){
        Vector vector = new Vector();
        newLocation.set(getLocation());

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++){ // cycle through every possible x and y in <-1, 1> range

                if (x == 0 && y == 0) //skip the vector(0,0) though
                    continue; 

                if (newLocation.isValidVector(vector))
                    newLocation.addVector(vector); //bounds check

                if (getActiveBoard().isSquareFree(newLocation))
                    possibleMoves.add(new MoveCandidate(getLocation(), vector));
                else
                    possibleMoves.add(new MoveCandidate(getLocation(), vector, CAPTURE));            
            }
                                  
                

        } 

    }

    

}
