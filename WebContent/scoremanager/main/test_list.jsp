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
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
				<form method="get">
					<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
						<div class="col-4">
						<a>科目情報</a>
							<label class="form-label" for="student-f1-select">入学年度</label>
							<select class="form-select " id="student-f1-select" name="f1">
								<option value="0">--------</option>
								<c:forEach var="year" items="${ent_year_set}">
									<option value="${year}"> <c:if test="${year==f1}">selected</c:if>${year}</option>
								</c:forEach>
							</select>
						</div>


						<div class="col-4">
							<lable class="form-label" for="student-f2-select">クラス</lable>
							<select class="form-select " id="student-f2-select" name="f2">
								<option value="0">--------</option>
								<c:forEach var="num" items="${class_num_set}">
									<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
									<option value="${num}"> <c:if test="${num==f2}">selected</c:if>${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-4">
							<lable class="form-label" for="student-f3-select">科目</lable>
							<select class="form-select " id="student-f3-select" name="f3">
								<option value="0">--------</option>
								<c:forEach var="subject" items="${class_subjectcd_set}">
									<option value="${subject.cd}"> <c:if test="${subject.cd==f2}">selected</c:if>${subject.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-2 text-center">
						<input type="hidden" name="f" value="sj">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<div class="mt-2 text-warning">${error.get("f1")}</div>


					</div>
			</form>
			<form method="get">
					<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
						<div class="col-4">
						<label>
						<p>学生情報</p>
						</label>
						<lable class="form-label" for="student-f3-select">学生番号</lable>
						<input class="form-control px-0 fs-5" autocomplete="off"
							id="id-input" maxlength="49" name="name" placeholder="学生番号を入力してください。"
							style="ime-mode: disabled" type="text" value="${name}" required />

						</div>
						<div class="col-2 text-center">
						<input type="hidden" name="f" value="st">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<div class="mt-2 text-warning">${error.get("f1")}</div>
					</div>
			</form>
			<label><p>科目情報を選択もしくは学生情報を入力して検索ボタンをクリックしてください。</p></label>
		</section>
	</c:param>

</c:import>
