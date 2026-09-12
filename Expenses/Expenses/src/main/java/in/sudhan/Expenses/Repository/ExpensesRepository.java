package in.sudhan.Expenses.Repository;

import in.sudhan.Expenses.Entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Integer> {

}
