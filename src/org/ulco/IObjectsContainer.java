package org.ulco;

import java.util.Vector;

/**
 * Created by cingouf on 20/11/17.
 */
public interface IObjectsContainer {
    Vector<GraphicsObject> getObjects();

    int sizeSimpleObjects();
}
