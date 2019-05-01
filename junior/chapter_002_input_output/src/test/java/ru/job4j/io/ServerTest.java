package ru.job4j.io;

import com.google.common.base.Joiner;
import org.junit.Test;
import ru.job4j.io.server.Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ServerTest {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String EMPTY_LINE = "";
    // user input
    public static final String HELLO_ORACLE = "Hello oracle";
    public static final String SOMETHING = "something";
    public static final String EXIT = "exit";
    // oracle phrases
    public static final String HELLO_USER = "Hello, dear friend, I'm oracle.";
    public static final String I_DON_T_UNDERSTAND = "I don't understand";

    @Test
    public void startHello() throws IOException {
        this.start(Joiner.on(LINE_SEPARATOR).join(
                HELLO_ORACLE,
                EXIT
        ), Joiner.on(LINE_SEPARATOR).join(
                HELLO_USER,
                EMPTY_LINE,
                EMPTY_LINE
        ));
    }

    @Test
    public void startSomething() throws IOException {
        this.start(Joiner.on(LINE_SEPARATOR).join(
                SOMETHING,
                EXIT
        ), Joiner.on(LINE_SEPARATOR).join(
                I_DON_T_UNDERSTAND,
                EMPTY_LINE,
                EMPTY_LINE
        ));
    }

    @Test
    public void startExit() throws IOException {
        this.start(EXIT, EMPTY_LINE);
    }

    private void start(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);

        server.start();

        assertThat(out.toString(), is(expected));
    }
}