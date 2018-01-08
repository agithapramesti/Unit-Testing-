
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;
import springboot.service.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceTest {



    private  TodoService todoService ;
    // jadi mock ini buat bikin objek boong2annya
    // yg mau ditest JANGAN di mock
    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp(){
//        this.todoRepository= new TodoRepository();
        MockitoAnnotations.initMocks(this);
        this.todoService = new TodoService(this.todoRepository);
    }
    @After
    public void tearDown(){
//memastikan hanya menggunakan todorepository
        Mockito.verifyNoMoreInteractions(todoRepository);
    }

    // @Test untuk menandai java bahwa ini unit testing
    // execute dengan console

    @Test
    public void getAllTest() throws Exception {
        // given
        // todo repo must return non empty list when getAll is called
        ArrayList<Todo> todos= new ArrayList<Todo>();
        todos.add(new Todo("Todo1", TodoPriority.MEDIUM));
        BDDMockito.given(todoRepository.getAll()).willReturn(todos);
        List<Todo> result = todoService.getAll();
        //assert that todo list is not null
        Assert.assertThat(result, Matchers.notNullValue());
        //assert that todo list is not empty
        Assert.assertThat(todos.isEmpty(), Matchers.equalTo(false));
        //verify
        BDDMockito.then(todoRepository).should().getAll();
//        List<Todo> result = todoService.getAll();
//
//
//        todoRepository.store(new Todo("Todo1", TodoPriority.MEDIUM));
//
//        List<Todo> todoList = todoService.getAll();
//        if(todoList.isEmpty()){
//            throw  new Exception("LIST KOSONG");
//        }
//        else
//        {
//            System.out.println("LIST ADA");
//        }

    }

    @Test
    public void saveTodoTest(){
        //given
        // todo repo must return true when saveTodo is called
        Todo todo = new Todo("myTodo", TodoPriority.MEDIUM);
        BDDMockito.given(todoRepository.store(todo)).willReturn(true);

        //when
        boolean result = todoService.saveTodo(todo.getName(), todo.getPriority());

        //then
        //assert that todo is return true
        Assert.assertThat(result, Matchers.equalTo(true));
        //verify
        BDDMockito.then(todoRepository).should().store(todo);
    }
}
