package com.example.meme;

import java.lang.Iterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/memes") // This means URL's start with /demo (after Application path)
public class MemeController {

    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    private MemeAppClient memeAppClient;
    
    /** 
     * Gets Random Meme from our SQL database.
     * @return Meme
    */
    @GetMapping("/original")
    public Meme getOriginalMeme() {
        Long qty = memeRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<Meme> memePage = memeRepository.findAll(PageRequest.of(idx, 1));
        Meme m = null;
        if (memePage.hasContent()) {
            m = memePage.getContent().get(0);
        }
        return m;
    }

    @GetMapping("/internet")
    public Joke getMemeFromInternet() throws Exception {
        return memeAppClient.getJokeFromInternet();
    }

}
