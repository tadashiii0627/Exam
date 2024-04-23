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
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

				<%-- 入学年度 --%>
				<form action ="StudentCreateExecute.action" method="get"><br>
						<div class="col-14">
							<label class="form-label" for="student-f1-select">入学年度</label>
							<select class="form-select " id="student-f1-select" name="ent_year">
								<option value="0">--------</option>
								<c:forEach var="year" items="${ent_year_set}">
									<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
									<option value="${year}"> <c:if test="${year==f1}">selected</c:if>${year}</option>
								</c:forEach>
							</select>
						</div>



						<%-- 学生番号 --%><br>
						<div class="form-floating mx-7">
						学生番号
						<lable>
						<input class="form-control px-0 fs-5" autocomplete="off"
							id="id-input" maxlength="20" name="no" placeholder="学生番号を入力してください。"
							style="ime-mode: disabled" type="text" value="${class_num}" required />
						</lable>
						</div>


						<%-- 氏名 --%><br>
						<div class="form-floating mx-7">
						氏名
						<lable>
						<input class="form-control px-0 fs-5" autocomplete="off"
							id="id-input" maxlength="20" name="name" placeholder="氏名を入力してください。"
							style="ime-mode: disabled" type="text" value="${name}" required />
						</lable>
						</div>


						<%-- クラス --%><br>
						<div class="col-14">
							<lable class="form-label" for="student-f2-select">クラス</lable>
							<select class="form-select " id="student-f2-select" name="class_num">
								<option value="0">--------</option>
								<c:forEach var="num" items="${class_num_set}">
									<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
									<option value="${num}"> <c:if test="${num==f2}">selected</c:if>${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-2 text-center"><br>
							<button class="btn btn-secondary" id="filter-button">登録して終了</button>
						</div>

				</form>
				<div class="my-2 text-start px-4">
					<a href="StudentCreate.action">戻る</a>
				</div>
			</section>
		</c:param>
	</c:import>





















