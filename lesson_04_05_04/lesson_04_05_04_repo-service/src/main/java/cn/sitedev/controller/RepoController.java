package cn.sitedev.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repo")
public class RepoController {
    @PutMapping("{pid}")
    public void repo(@PathVariable String pid) {
        System.out.println("扣减库存, 商品id:" + pid);
    }
}
