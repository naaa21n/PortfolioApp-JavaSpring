package com.example.portfolioapi.controller.task;

// =========================
// Entity Import
// =========================

// Taskエンティティ
import com.example.portfolioapi.entity.task.Task;

// Memoエンティティ
import com.example.portfolioapi.entity.task.Memo;

// =========================
// Repository Import
// =========================

// Taskテーブル操作用Repository
import com.example.portfolioapi.repository.task.TaskRepository;

// Memoテーブル操作用Repository
import com.example.portfolioapi.repository.task.MemoRepository;

// =========================
// Spring MVC Import
// =========================

import org.springframework.web.bind.annotation.*;

// =========================
// Java Import
// =========================

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// =========================
// Task Controller
// =========================
//
// タスクページで使用するAPI Controller
//
// このControllerでは以下の2種類のデータを扱う
//
// ・tasks
// ・memos
//
// URL:
//
// tasks:
// GET    /api/tasks
// POST   /api/tasks
// PUT    /api/tasks/{id}
// DELETE /api/tasks/{id}
// PUT    /api/tasks/{id}/done
//
// memos:
// GET    /api/memos
// POST   /api/memos
// PUT    /api/memos/{id}
// DELETE /api/memos/{id}
//
@RestController
@RequestMapping("/api")

// Next.jsが別ポートで動く場合に必要になることがある
// 例:
// @CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    // =========================
    // Repository
    // =========================

    // Taskテーブル操作用Repository
    private final TaskRepository taskRepository;

    // Memoテーブル操作用Repository
    private final MemoRepository memoRepository;

    // =========================
    // Constructor Injection
    // =========================
    //
    // Springが自動でRepositoryを注入する
    //
    public TaskController(
            TaskRepository taskRepository,
            MemoRepository memoRepository
    ) {
        this.taskRepository = taskRepository;
        this.memoRepository = memoRepository;
    }

    // ============================================================
    // Task API
    // ============================================================

    // =========================
    // タスク一覧取得
    // =========================
    //
    // GET:
    // /api/tasks
    //
    @GetMapping("/tasks")
    public List<Task> getTasks() {

        // 注意:
        // 現時点では全ユーザーのタスクを返す
        // JWT導入後は userId で絞り込む必要がある
        return taskRepository.findAll();
    }

    // =========================
    // タスク1件取得
    // =========================
    //
    // GET:
    // /api/tasks/{id}
    //
    @GetMapping("/tasks/{id}")
    public Task getTask(
            @PathVariable UUID id
    ) {
        return taskRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // タスク追加
    // =========================
    //
    // POST:
    // /api/tasks
    //
    @PostMapping("/tasks")
    public List<Task> addTask(
            @RequestBody Task task
    ) {

        // JSONデータをDB保存
        //
        // SQLイメージ:
        // INSERT INTO tasks ...
        taskRepository.save(task);

        // 保存後一覧返却
        return taskRepository.findAll();
    }

    // =========================
    // タスク更新
    // =========================
    //
    // PUT:
    // /api/tasks/{id}
    //
    @PutMapping("/tasks/{id}")
    public Task updateTask(
            @PathVariable UUID id,
            @RequestBody Task request
    ) {

        Optional<Task> optional =
                taskRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Task task = optional.get();

        task.setTitle(request.getTitle());
        task.setTaskDate(request.getTaskDate());
        task.setDeadline(request.getDeadline());
        task.setContent(request.getContent());
        task.setCompleted(request.isCompleted());

        return taskRepository.save(task);
    }

    // =========================
    // タスク削除
    // =========================
    //
    // DELETE:
    // /api/tasks/{id}
    //
    @DeleteMapping("/tasks/{id}")
    public List<Task> deleteTask(
            @PathVariable UUID id
    ) {

        // 指定ID削除
        //
        // SQLイメージ:
        // DELETE FROM tasks
        // WHERE id = ?
        taskRepository.deleteById(id);

        // 削除後一覧返却
        return taskRepository.findAll();
    }

    // =========================
    // タスク完了
    // =========================
    //
    // PUT:
    // /api/tasks/{id}/done
    //
    @PutMapping("/tasks/{id}/done")
    public List<Task> completeTask(
            @PathVariable UUID id
    ) {

        // IDでtask検索
        //
        // 見つからなければ例外
        Task task =
                taskRepository
                        .findById(id)
                        .orElseThrow();

        // Entity側のフィールド名は done ではなく completed
        //
        // completed = true に変更
        task.setCompleted(true);

        // DB更新
        //
        // SQLイメージ:
        // UPDATE tasks
        // SET completed = true
        // WHERE id = ?
        taskRepository.save(task);

        // 更新後一覧返却
        return taskRepository.findAll();
    }

    // =========================
    // タスク未完了に戻す
    // =========================
    //
    // PUT:
    // /api/tasks/{id}/undone
    //
    @PutMapping("/tasks/{id}/undone")
    public List<Task> incompleteTask(
            @PathVariable UUID id
    ) {

        Task task =
                taskRepository
                        .findById(id)
                        .orElseThrow();

        task.setCompleted(false);

        taskRepository.save(task);

        return taskRepository.findAll();
    }

    // ============================================================
    // Memo API
    // ============================================================

    // =========================
    // メモ一覧取得
    // =========================
    //
    // GET:
    // /api/memos
    //
    @GetMapping("/memos")
    public List<Memo> getMemos() {

        // 注意:
        // 現時点では全ユーザーのメモを返す
        // JWT導入後は userId で絞り込む必要がある
        return memoRepository.findAll();
    }

    // =========================
    // メモ1件取得
    // =========================
    //
    // GET:
    // /api/memos/{id}
    //
    @GetMapping("/memos/{id}")
    public Memo getMemo(
            @PathVariable UUID id
    ) {
        return memoRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // メモ追加
    // =========================
    //
    // POST:
    // /api/memos
    //
    @PostMapping("/memos")
    public List<Memo> addMemo(
            @RequestBody Memo memo
    ) {

        // JSONデータをDB保存
        //
        // SQLイメージ:
        // INSERT INTO memos ...
        memoRepository.save(memo);

        // 保存後一覧返却
        return memoRepository.findAll();
    }

    // =========================
    // メモ更新
    // =========================
    //
    // PUT:
    // /api/memos/{id}
    //
    @PutMapping("/memos/{id}")
    public Memo updateMemo(
            @PathVariable UUID id,
            @RequestBody Memo request
    ) {

        Optional<Memo> optional =
                memoRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Memo memo = optional.get();

        memo.setTitle(request.getTitle());
        memo.setContent(request.getContent());

        return memoRepository.save(memo);
    }

    // =========================
    // メモ削除
    // =========================
    //
    // DELETE:
    // /api/memos/{id}
    //
    @DeleteMapping("/memos/{id}")
    public List<Memo> deleteMemo(
            @PathVariable UUID id
    ) {

        // 指定ID削除
        //
        // SQLイメージ:
        // DELETE FROM memos
        // WHERE id = ?
        memoRepository.deleteById(id);

        // 削除後一覧返却
        return memoRepository.findAll();
    }
}