package com.Login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/chat")
public class ChatController {

    // Mock data: messages stored in memory
    private static final Map<String, List<String>> chats = new HashMap<>();

    static {
        chats.put("john", new ArrayList<>(Arrays.asList("Hi, I am John!", "How are you?")));
        chats.put("jane", new ArrayList<>(Arrays.asList("Hey, Jane here.", "What's up?")));
        chats.put("alex", new ArrayList<>(Arrays.asList("Yo! Alex here.", "Let's catch up.")));
    }

    @GetMapping("/{username}")
    public String chatWithUser(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("messages", chats.getOrDefault(username, new ArrayList<>()));
        return "Chat";
    }

    @PostMapping("/{username}/send")
    public String sendMessage(@PathVariable String username,
                              @RequestParam String message) {
        chats.computeIfAbsent(username, k -> new ArrayList<>()).add("Me: " + message);
        return "redirect:/chat/" + username;
    }
}
