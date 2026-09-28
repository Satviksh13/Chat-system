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
        chats.put("Satvik", new ArrayList<>(Arrays.asList("Hi, I am Satvik!", "How are you?")));
        chats.put("Dishika", new ArrayList<>(Arrays.asList("Hey, Dishika here.", "What's up?")));
        chats.put("Shivansh", new ArrayList<>(Arrays.asList("Yo! shivansh here.", "Let's catch up.")));
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
