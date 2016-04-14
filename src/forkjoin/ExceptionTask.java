package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ExceptionTask extends RecursiveTask<String> {

	private String name;

	public ExceptionTask(String name) {
		this.name = name;
	}

	protected String compute() {
		System.out.println("Doing: " + name);
		return "Exception: " + name;
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		List<ExceptionTask> tasks = new ArrayList<ExceptionTask>();

		ExceptionTask aaaTask = new ExceptionTask("aaa");
		tasks.add(aaaTask);
		forkJoinPool.execute(aaaTask);

		ExceptionTask bbbTask = new ExceptionTask("bbb");
		tasks.add(bbbTask);
		forkJoinPool.execute(bbbTask);

		ExceptionTask cccTask = new ExceptionTask("ccc");
		tasks.add(cccTask);
		forkJoinPool.execute(cccTask);

		ExceptionTask dddTask = new ExceptionTask("ddd");
		tasks.add(dddTask);
		forkJoinPool.execute(dddTask);

		try {
			for (ExceptionTask task : tasks) {
				System.out.println(task.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
