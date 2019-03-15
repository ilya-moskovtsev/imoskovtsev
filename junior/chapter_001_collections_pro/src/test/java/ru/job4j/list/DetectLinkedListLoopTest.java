package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static ru.job4j.list.DetectLinkedListLoop.Node;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DetectLinkedListLoopTest {

    private DetectLinkedListLoop<Integer> detect;
    private Node<Integer> firstNode;
    private Node<Integer> secondNode;
    private Node<Integer> thirdNode;
    private Node<Integer> forthNode;

    @Before
    public void setUp() {
        detect = new DetectLinkedListLoop<>();
        firstNode = detect.createNode(1);
        secondNode = detect.createNode(2);
        thirdNode = detect.createNode(3);
        forthNode = detect.createNode(4);
    }

    @Test
    public void when4To1ThanHasLoopTrue() {
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = forthNode;
        forthNode.next = firstNode;

        assertThat(detect.hasLoop(firstNode), is(true));
    }

    @Test
    public void when3To2ThanHasLoopTrue() {
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = secondNode;

        assertThat(detect.hasLoop(firstNode), is(true));
    }

    @Test
    public void hasLoopFalse() {
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = forthNode;

        assertThat(detect.hasLoop(firstNode), is(false));
    }
}