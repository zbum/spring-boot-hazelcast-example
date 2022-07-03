package kr.co.manty.study.springboot.hazelcastex1;

import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.ItemEvent;
import com.hazelcast.collection.ItemListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@Component
public class NameCommand {

    private final IQueue<String> nameQueue;

    @EventListener(value = ApplicationReadyEvent.class)
    public void onApplicationReady(ApplicationReadyEvent event) {
        new Thread(() ->
                new Scanner(System.in)
                        .useDelimiter("\n")
                        .tokens()
                        .map(it -> it.split(" ", 2))
                        .forEach(it -> {
                            switch (it[0].toLowerCase()) {
                                case "add" : {
                                    if ( it.length == 2) {
                                        nameQueue.add(it[1]);
                                    }
                                    break;
                                }
                                case "poll" : {
                                    nameQueue.poll();
                                    break;
                                }
                                case "exit" : {
                                    System.exit(0);
                                    break;
                                }
                                default: {
                                    //skip!!
                                }
                            }
                        }))
                .start();
    }

    @PostConstruct
    public void afterPropertySet() {
        nameQueue.addItemListener(new ItemListener<>() {
            @Override
            public void itemAdded(ItemEvent<String> item) {
                System.out.println("\n[ADDED:" + item.getItem() + "]");
            }

            @Override
            public void itemRemoved(ItemEvent<String> item) {
                System.out.println("\n[REMOVED:" + item.getItem() + "]");
            }
        }, true);
    }

}