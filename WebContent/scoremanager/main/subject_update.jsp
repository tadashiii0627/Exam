<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
		</c:param>

		<c:param name="scripts"></c:param>

		<c:param name="content">
			<section class="me-4">
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

						<%-- 科目コード --%>
						<form action ="StudentUpdateExecute.action" method="get"><br><br>
						<div class="form-floating mx-7">
						科目コード
						<lable>
						<input readonly class="" autocomplete="off"
							id="id-input" maxlength="20" name="no" placeholder="科目コードを入力してください。"
							style="ime-mode: disabled" type="text" value="${code}" required />
						</lable>
						</div>


						<%-- 科目名 --%><br>
						<div class="form-floating mx-7">
						科目名
						<lable>
						<input class="form-control px-0 fs-5" autocomplete="off"
							id="id-input" maxlength="20" name="name" placeholder="科目名を入力してください。"
							style="ime-mode: disabled" type="text" value="${name}" required />
						</lable>
						</div>


						<br>
						<div class="col-2 text-center"><br>
							<button class="btn btn-primary" id="filter-button">変更</button>
						</div>

				</form>
				<div class="my-2 text-start px-4">
					<a href="SubjectListAction.java">戻る</a>
				</div>
			</section>
		</c:param>
	</c:import>