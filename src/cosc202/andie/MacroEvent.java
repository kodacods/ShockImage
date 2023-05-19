package cosc202.andie;

import java.io.Serializable;

public class MacroEvent implements Serializable {
    private long timestamp;
    private MacroEventType eventType;
    private int keyCode;
    private int button;
    private int x;
    private int y;

    public MacroEvent(long timestamp, MacroEventType eventType, int keyCode, int button, int x, int y) {
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.keyCode = keyCode;
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public MacroEventType getEventType() {
        return eventType;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getButton() {
        return button;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public enum MacroEventType {
        KEY_PRESSED,
        KEY_RELEASED,
        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_CLICKED
    }
}
