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
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

				<%-- 入学年度 --%>
				<form action ="SubjectDeleteExecute.action" method="get"><br>

						<%-- 科目コード --%><br>
						<div class="form-floating mx-7">
						科目コード
						<lable>
						<p>「${name }(${cd })」を削除してもよろしいですか</p>
						</lable>
						</div>

						<br>
						<div class="col-2 text-center"><br>
							<button class="btn btn-danger" id="filter-button">削除</button>
						</div>

				</form>
				<div class="my-2 text-start px-9">
					<a href="SubjectList.action">戻る</a>
				</div>
			</section>
		</c:param>
	</c:import>