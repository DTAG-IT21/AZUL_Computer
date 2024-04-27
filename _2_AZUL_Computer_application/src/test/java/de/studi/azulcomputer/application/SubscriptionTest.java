package de.studi.azulcomputer.application;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SubscriptionTest{
    @Test
    public void testSubscription(){
        Game game = new Game();
        Listener mockListener = mock(Listener.class);

        game.subscribe(mockListener);
        verify(mockListener, times(1)).update();
    }
}
