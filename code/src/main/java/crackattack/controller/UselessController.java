package crackattack.controller;

import crackattack.events.EventDispatcher;

public class UselessController extends AbstractController {
    public UselessController(EventDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void tick() {}
}
