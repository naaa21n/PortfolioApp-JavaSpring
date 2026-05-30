package com.example.portfolioapi.controller;

// Taskエンティティ
import com.example.portfolioapi.entity.Task;

// Taskテーブル操作用Repository
import com.example.portfolioapi.repository.TaskRepository;

// Spring MVC
import org.springframework.web.bind.annotation.*;

// List用
import java.util.List;

// REST API Controller
@RestController

// このControllerの共通URL
// 例:
// /api/tasks
@RequestMapping("/api")

// Next.js(localhost:3000)からのアクセス許可
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    // =========================
    // Repository
    // =========================

    // Taskテーブル操作
    private final TaskRepository taskRepository;

    // =========================
    // Constructor Injection
    // =========================

    // Springが自動でRepositoryを注入
    public TaskController(
            TaskRepository taskRepository
    ) {

        this.taskRepository =
                taskRepository;
    }

    // =========================
    // タスク一覧取得
    // =========================

    // GET:
    // /api/tasks
    @GetMapping("/tasks")

    public List<Task> getTasks() {

        // taskテーブル全件取得
        //
        // SQLイメージ:
        // SELECT * FROM task;
        return taskRepository.findAll();
    }

    // =========================
    // タスク追加
    // =========================

    // POST:
    // /api/tasks
    @PostMapping("/tasks")

    public List<Task> addTask(
            @RequestBody Task task
    ) {

        // JSONデータをDB保存
        //
        // SQLイメージ:
        // INSERT INTO task ...
        taskRepository.save(task);

        // 保存後一覧返却
        return taskRepository.findAll();
    }

    // =========================
    // タスク削除
    // =========================

    // DELETE:
    // /api/tasks/1
    @DeleteMapping("/tasks/{id}")

    public List<Task> deleteTask(
            @PathVariable String id
    ) {

        // 指定ID削除
        //
        // SQLイメージ:
        // DELETE FROM task
        // WHERE id = ?
        taskRepository.deleteById(id);

        // 削除後一覧返却
        return taskRepository.findAll();
    }

    // =========================
    // タスク完了
    // =========================

    // PUT:
    // /api/tasks/1/done
    @PutMapping("/tasks/{id}/done")

    public List<Task> completeTask(
            @PathVariable String id
    ) {

        // IDでtask検索
        //
        // 見つからなければエラー
        Task task =
                taskRepository
                        .findById(id)
                        .orElseThrow();

        // done=trueへ変更
        task.setDone(true);

        // DB更新
        //
        // SQLイメージ:
        // UPDATE task
        // SET done = true
        // WHERE id = ?
        taskRepository.save(task);

        // 更新後一覧返却
        return taskRepository.findAll();
    }
}


//Task(タスク) を管理するAPI Controller
//タスクデータのCRUD担当

//このままだと全ユーザーのtaskを返してしまう危険があるので改良すべき
