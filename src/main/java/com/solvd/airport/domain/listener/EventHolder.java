package com.solvd.airport.domain.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHolder {

//    private static final Map<EventType, List<User>> HOLDER = new HashMap<>(); // храняться пользователи подписанные на каналы
    private static final Map<EventType, List<IEvent>> HOLDER = new HashMap<>();

    public static void subscribe(IEvent event, EventType type) {
        if (HOLDER.get(type) == null) {
            HOLDER.put(type, new ArrayList<>()); // если еще никто не сабскрайбнулся то создать пустой список
        }
        HOLDER.get(type).add(event); // add(user); // добавляем в лист пользователя
    }

    public static void unsubscribe(User user, EventType type) {
        //...берем по тайп и удаляем юзера из листа
    }

    public static void notify(EventType type) {
        List<IEvent> events = HOLDER.get(type);
        if (events != null) {
            events.forEach(event -> event.onEvent(type));
        }
    }
}
