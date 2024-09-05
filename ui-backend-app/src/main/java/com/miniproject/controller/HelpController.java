	package com.miniproject.controller;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;
	
	import com.miniproject.model.Help;
	import com.miniproject.service.HelpService;
	
	@RestController
	@CrossOrigin("*")
	public class HelpController {
	    @Autowired
	    private HelpService helpService;
	
	    @PostMapping("/save-help")
	    public Help saveHelp(@RequestBody Help help) {
	        return helpService.saveHelp(help);
	    }
	
	    @GetMapping("/get-all-requests")
	    public List<Help> getAllHelps() {
	        return helpService.getAllHelps();
	    }
	
	    @GetMapping("/get-request")
	    public Help getHelp(@RequestParam("id") Long id) {
	        return helpService.getHelp(id);
	    }
	
	    @DeleteMapping("/delete-request/{id}")
	    public void deleteHelp(@PathVariable("id") Long id) {
	        helpService.deleteHelp(id);
	    }
	
	    @PutMapping("/update-request/{id}")
	    public Help updateHelp(@PathVariable("id") Long id, @RequestBody Help updatedHelp) {
	        return helpService.updateHelp(id, updatedHelp);
	    }
	}
	
