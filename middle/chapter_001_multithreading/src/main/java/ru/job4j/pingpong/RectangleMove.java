package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private boolean moveRight = true;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (true) {
            if (moveRight) {
                moveRight();
            } else {
                moveLeft();
            }
        }
    }

    private void moveLeft() {
        while (this.rect.getX() > 0) {
            this.rect.setX(this.rect.getX() - 1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        moveRight = true;
    }

    private void moveRight() {
        while (this.rect.getX() < 290) {
            this.rect.setX(this.rect.getX() + 1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        moveRight = false;
    }
}
