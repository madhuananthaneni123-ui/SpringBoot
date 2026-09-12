package in.sudhan.Expenses.Service;

import in.sudhan.Expenses.Dto.ExpensesRequestDto;
import in.sudhan.Expenses.Dto.ExpensesResponseDto;
import in.sudhan.Expenses.Entity.Expenses;
import in.sudhan.Expenses.Repository.ExpensesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {
    private ExpensesRepository expensesRepository;
    public ExpensesService(ExpensesRepository expensesRepository){
        this.expensesRepository=expensesRepository;
    }
    public ExpensesResponseDto createexpenses(ExpensesRequestDto expenses){

        Expenses repo=expensesRepository.save(mapToEntity(expenses));
        return mapToDto(repo);
    }
    public ExpensesResponseDto getexpenses(Integer id){
        Optional<Expenses> re=expensesRepository.findById(id);
        if(re.isEmpty()) return null;
        return mapToDto(re.get());
    }
    public List<ExpensesResponseDto> getexpensesall(){
        List<Expenses> all=expensesRepository.findAll();
        return all.stream().
                map(this::mapToDto).toList();
    }
    public ExpensesResponseDto upexpenses(Integer id,ExpensesRequestDto ex){
        Optional<Expenses> user=expensesRepository.findById(id);
        if(user.isEmpty()) return null;
        Expenses exp=user.get();
        exp.setAmount(ex.getAmount());
        exp.setCategory(ex.getCategory());
        exp.setDate(ex.getDate());
        exp.setDescription(ex.getDescription());
        exp.setTitle(ex.getTitle());
        exp.setUpdatedAt(LocalDateTime.now());
        Expenses req=expensesRepository.save(exp);
        return mapToDto(req);
    }
    public boolean deleteexpenses(Integer id){
        Optional<Expenses> opt=expensesRepository.findById(id);
        if(opt.isEmpty()) return false;
        expensesRepository.deleteById(id);
        return true;
    }
    public Expenses mapToEntity(ExpensesRequestDto expensesRequestDto) {
        Expenses expenses=new Expenses();
        expenses.setTitle(expensesRequestDto.getTitle());
        expenses.setDescription(expensesRequestDto.getDescription());
        expenses.setDate(expensesRequestDto.getDate());
        expenses.setCategory(expensesRequestDto.getCategory());
        expenses.setAmount(expensesRequestDto.getAmount());
        expenses.setCreatedAt(LocalDateTime.now());
        expenses.setUpdatedAt(LocalDateTime.now());
        return expenses;
    }
    public ExpensesResponseDto mapToDto(Expenses expenses){
        ExpensesResponseDto expensesResponseDto=new ExpensesResponseDto();
        expensesResponseDto.setId(expenses.getId());
        expensesResponseDto.setAmount(expenses.getAmount());
        expensesResponseDto.setCategory(expenses.getCategory());
        expensesResponseDto.setDate(expenses.getDate());
        expensesResponseDto.setDescription(expenses.getDescription());
        expensesResponseDto.setCreatedAt(expenses.getCreatedAt());
        expensesResponseDto.setTitle(expenses.getTitle());
        return expensesResponseDto;
    }
}
