package com.balaban.minesweeper;

import java.util.ArrayList;

/**
 * The interface UserInput is needed to define ways of user input
 */
public interface UserInput {

    /**
     * Get coordinates of the target cell from user, also get flag set/unset status
     *
     * @return array list with row cell position, column cell position and input mode - click or flag
     */
    ArrayList getTargetCell();

}
