package crackattack.controller;

import crackattack.Application;
import crackattack.events.EventDispatcher;

public class UselessController extends AbstractController {
    public UselessController(EventDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void tick() {
        return;
    }
}
