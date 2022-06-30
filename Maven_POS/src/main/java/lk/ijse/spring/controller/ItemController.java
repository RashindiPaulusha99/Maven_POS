package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveItem(@ModelAttribute ItemDTO itemDTO){
        itemService.saveItem(itemDTO);
        return new ResponseUtil(200, "Item Saved.",null);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchItem(@PathVariable String id){
        return new ResponseUtil(200, "Ok.",itemService.searchItem(id));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateItem(@RequestBody ItemDTO itemDTO){
        itemService.updateItem(itemDTO);
        return new ResponseUtil(200, "Item Updated.",null);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteItem(@RequestParam String id){
        itemService.deleteItem(id);
        return new ResponseUtil(200, "Item Deleted.", null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllItems(){
        return new ResponseUtil(200, "Ok", itemService.getAllItems());
    }

    @GetMapping(params = {"test"})
    public ResponseUtil generateItemCode(@RequestParam String test) {
        return new ResponseUtil(200, "Ok", itemService.generateItemCode());
    }

}
