package com.nhnacademy.edu.springboot.hazelcastex1;

import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.ItemEvent;
import com.hazelcast.collection.ItemListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jline.terminal.Terminal;
import org.springframework.shell.Shell;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Clear;

import javax.annotation.PostConstruct;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class NameCommand {

    private final IQueue<String> nameQueue;

    @ShellMethod(value="test")
    public String name() {
        return nameQueue.poll();
    }

    @ShellMethod(value="test")
    public String addName(String name) {
        nameQueue.add(name);
        return name;
    }

    @PostConstruct
    public void afterPropertySet() {
        nameQueue.addItemListener(new ItemListener<>() {
            @Override
            public void itemAdded(ItemEvent<String> item) {
                System.out.println("\n[ADDED:"+item.getItem()+"]");

            }

            @Override
            public void itemRemoved(ItemEvent<String> item) {
                System.out.println("\n[REMOVED:"+item.getItem()+"]");
            }
        }, true);
    }

}