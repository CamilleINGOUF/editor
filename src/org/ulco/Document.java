package org.ulco;

import java.util.Vector;

public class Document {
    public Document() {
        m_layers = new Vector<Layer>();
    }

    public Layer createLayer() {
        Layer layer = new Layer();

        m_layers.add(layer);
        return layer;
    }

    public int getLayerNumber() {
        return m_layers.size();
    }

    public int getObjectNumber() {
        int size = 0;

        for (int i = 0; i < m_layers.size(); ++i) {
            size += m_layers.elementAt(i).getObjectNumber();
        }
        return size;
    }

    public void parseLayers(String layersStr) {
        while (!layersStr.isEmpty()) {
            int separatorIndex = Util.searchSeparator(layersStr);
            String layerStr;

            if (separatorIndex == -1) {
                layerStr = layersStr;
            } else {
                layerStr = layersStr.substring(0, separatorIndex);
            }
            m_layers.add(JSON.parseLayer(layerStr));
            if (separatorIndex == -1) {
                layersStr = "";
            } else {
                layersStr = layersStr.substring(separatorIndex + 1);
            }
        }
    }

    public Vector<Layer> getLayers()
    {
        return m_layers;
    }

    private Vector<Layer> m_layers;
}
