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
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
				<div class="my-2 text-end px-4">
					<a href="StudentCreate.action">新規登録</a>
				</div>
				<form method="get">
					<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
						<div class="col-4">
							<label class="form-label" for="student-f1-select">入学年度</label>
							<select class="form-select " id="student-f1-select" name="f1">
								<option value="0">--------</option>
								<c:forEach var="year" items="${year}">
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
								<c:forEach var="num" items="${class_subject.cd_set}">
									<option value="${subject.cd}"> <c:if test="${subject.cd==f2}">selected</c:if>${subject.cd}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-4">
							<lable class="form-label" for="student-f4-select">回数</lable>
							<select class="form-select " id="student-f4-select" name="f4">
								<option value="0">--------</option>
								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}"> <c:if test="${num==f2}">selected</c:if>${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<div class="mt-2 text-warning">${error.get("f1")}</div>
					</div>
				</form>
				<c:choose>
					<c:when test="${students.size()>0}">
						<div>検索結果:${students.size()}件</div>
						<table class="table table-hover">
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学年番号</th>
								<th>氏名</th>
								<th>点数</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach var="test" items="${students}">
								<tr>
									<th>${test.studen.entYear}</th>
									<td>${test.student.classNum}</td>
									<td>${test.student.no}</td>
									<td>${test.student.name}</td>
									<td><input class="form-control px-0 fs-5" autocomplete="off"
										id="id-input" maxlength="20" name="point_${学生番号 }]"
										style="ime-mode: disabled" type="text" value="${name}" required /></td>
									<td></td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
				</c:choose>
			</section>
		</c:param>
	</c:import>


















