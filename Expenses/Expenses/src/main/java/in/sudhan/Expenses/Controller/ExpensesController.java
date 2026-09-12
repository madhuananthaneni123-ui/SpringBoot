package in.sudhan.Expenses.Controller;

import in.sudhan.Expenses.Dto.ExpensesRequestDto;
import in.sudhan.Expenses.Dto.ExpensesResponseDto;
import in.sudhan.Expenses.Entity.Expenses;
import in.sudhan.Expenses.Service.ExpensesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {
    private ExpensesService expensesService;
    public ExpensesController(ExpensesService expensesService){
        this.expensesService=expensesService;
    }
    @PostMapping("/create")
    public ResponseEntity<ExpensesResponseDto> createexpenses(@RequestBody ExpensesRequestDto expenses){
        ExpensesResponseDto expenses1=expensesService.createexpenses(expenses);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenses1);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ExpensesResponseDto> getexpenses(@PathVariable Integer id){
        ExpensesResponseDto expenses=expensesService.getexpenses(id);
        if(expenses==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(expenses);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ExpensesResponseDto>> getall(){
        List<ExpensesResponseDto> all=expensesService.getexpensesall();
        if (all==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(all);
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<ExpensesResponseDto> updateexpenses(@PathVariable Integer id, @RequestBody ExpensesRequestDto expenses) {
        ExpensesResponseDto expenses1=expensesService.upexpenses(id,expenses);
        if(expenses1==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(expenses1);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean del=expensesService.deleteexpenses(id);
        if(del==false) return ResponseEntity.ok("Not found");
        return ResponseEntity.ok("deleted");
    }
}
