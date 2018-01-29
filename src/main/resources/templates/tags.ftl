<!DOCTYPE html>
<html lang="en" ng-app="Klov">
	<#include 'partials/head.ftl'>
	<body>
		<div class="app tags" id="app">
			<#include 'partials/sidenav.ftl'>
			<!-- content -->
			<div id="content" class="app-content box-shadow-z2 bg pjax-container" role="main">
				<div class="app-header white bg b-b">
					<div class="navbar" data-pjax>
						<a data-toggle="modal" data-target="#aside" class="navbar-item pull-left hidden-lg-up p-r m-a-0">
						<i class="material-icons">reorder</i>
						</a>
						<div class="navbar-item pull-left h5" id="pageTitle">Tags</div>
						<#include 'partials/navbar-right.ftl'>
					</div>
				</div>
				<#include 'partials/footer.ftl'>
				<div class="app-body">
					<#if project.isPresent()>
						<!-- ############ PAGE START-->
						<div class="row-col" ng-controller="CategoryController" ng-init="init()">
							<div class="padding">
								<div class="row">
									<div class="col-sm-6">
										<div id="test-count-by-tag-container" class="box">
											<div class="box-header">
												<h3>Test Count by Tag</h3>
												<small>{{testLengthSelection}}</small>
											</div>
											<div class="box-tool">
												<ul class="nav">
		                                            <li class="nav-item inline">
		                                                <a class="nav-link" alt="Bar chart" title="Bar chart" href="#" ng-click="changeTimeTakenChartType('testLength', 'bar')">
		                                                	<i class="material-icons">insert_chart</i>
		                                                </a>
		                                            </li>
		                                            <li class="nav-item inline">
		                                                <a class="nav-link" alt="Pie chart" title="View failed tests" href="#" ng-click="changeTimeTakenChartType('testLength', 'pie')">
		                                                	<i class="fa fa-pie-chart"></i>
		                                                </a>
		                                            </li>
		                                            <li class="nav-item inline">
		                                            	<a data-toggle="dropdown" class="btn btn-xs rounded white dropdown-toggle">Select builds</a>
														<div class="dropdown-menu pull-right">
															<a class="dropdown-item" href="#" ng-click="getCategoryTestLengthAverageOverNReports(1)">Last build</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTestLengthAverageOverNReports(5)">Average: Last 5 builds</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTestLengthAverageOverNReports(10)">Average: Last 10 builds</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTestLengthAverageOverNReports(15)">Average: Last 15 builds</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTestLengthAverageOverNReports(20)">Average: Last 20 build</a>
															<div class="dropdown-divider"></div>
															<a class="dropdown-item" href="#" ng-click="getCategoryTestLengthAverageOverNReports(1)">Reset</a>
														</div>
		                                            </li>
		                                        </ul>							
											</div>
											<div class="box-body">
												<canvas id="test-count-by-tag" 
													width="{{testCountByTagContainerWidth}}" 
													height="325" 
													class="chart-base" 
													chart-data="testCountByTagData" 
													chart-labels="testCountByTagLabels" 
													chart-options="testCountByTagOptions" 
													chart-type="testCountChartType">
												</canvas>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div id="time-taken-by-tag-container" class="box">
											<div class="box-header">
												<h3>Time taken by Tag</h3>
												<small>{{timeTakenSelection}}</small>
											</div>
											<div class="box-tool">
												<ul class="nav">
		                                            <li class="nav-item inline">
		                                                <a class="nav-link" alt="Bar chart" title="Bar chart" href="#" ng-click="changeTimeTakenChartType('timeTaken', 'bar')">
		                                                	<i class="material-icons">insert_chart</i>
		                                                </a>
		                                            </li>
		                                            <li class="nav-item inline">
		                                                <a class="nav-link" alt="Pie chart" title="View failed tests" href="#" ng-click="changeTimeTakenChartType('timeTaken', 'pie')">
		                                                	<i class="fa fa-pie-chart"></i>
		                                                </a>
		                                            </li>
		                                            <li class="nav-item inline">
		                                            	<a data-toggle="dropdown" class="btn btn-xs rounded white dropdown-toggle">Select builds</a>
														<div class="dropdown-menu pull-right">
															<a class="dropdown-item" href="#" ng-click="getCategoryTimeTakenAverageOverNReports(1)">Last build</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTimeTakenAverageOverNReports(5)">Average: Last 5 builds</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTimeTakenAverageOverNReports(10)">Average: Last 10 builds</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTimeTakenAverageOverNReports(15)">Average: Last 15 builds</a>
															<a class="dropdown-item" href="#" ng-click="getCategoryTimeTakenAverageOverNReports(20)">Average: Last 20 build</a>
															<div class="dropdown-divider"></div>
															<a class="dropdown-item" href="#" ng-click="getCategoryTimeTakenAverageOverNReports(1)">Reset</a>
														</div>
		                                            </li>
		                                        </ul>							
											</div>
											<div class="box-body">
												<canvas id="time-taken-by-tag" 
													width="{{timeTakenByTagContainerWidth}}" 
													height="325" 
													class="chart-base" 
													chart-data="timeTakenByTagData" 
													chart-labels="timeTakenByTagLabels" 
													chart-options="timeTakenByTagOptions"
													chart-type="timeTakenChartType">
												</canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					<!-- ############ PAGE END-->
					<#else>
						<div class="padding">
							<div class="row">
								<div class="col-sm-4">
          							<p class="m-a-0">Please <a href="/projects">select a project</a> to get started.</p>
							    </div>
						    </div>
					    </div>
					</#if>
				</div>
			</div>
			<!-- / -->
			<#include 'partials/switcher.ftl'>
		</div>
		<#include 'partials/scripts.ftl'>
		<#include 'partials/angular.ftl'>
	</body>
</html>