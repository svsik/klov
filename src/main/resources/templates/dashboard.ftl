<!DOCTYPE html>
<html lang="en" ng-app="Klov">
	<#include 'partials/head.ftl'>
	<body>
		<div class="app dashboard" id="app">
			<#include 'partials/sidenav.ftl'>
			<!-- content -->
			<div id="content" class="app-content box-shadow-z2 bg pjax-container" role="main">
				<div class="app-header white bg b-b">
					<div class="navbar" data-pjax>
						<a data-toggle="modal" data-target="#aside" class="navbar-item pull-left hidden-lg-up p-r m-a-0">
						<i class="material-icons">reorder</i>
						</a>
						<div class="navbar-item pull-left h5" id="pageTitle">Dashboard</div>
						<#include 'partials/navbar-right.ftl'>
					</div>
				</div>
				<#include 'partials/footer.ftl'>
				<div class="app-body">
					<!-- ############ PAGE START-->
					<div class="row-col" ng-controller="ReportController" ng-init="getReportList(1)">
						<div class="col-lg b-r">
							<div class="row no-gutter">
								<div class="col-xs-6 col-sm-3 b-r b-b">
									<div class="padding">
										<div>
											<span class="pull-right"><i class="fa fa-caret-up text-primary m-y-xs"></i></span>
											<span class="text-muted l-h-1x"><i class="ion-ios-grid-view text-muted"></i></span>
										</div>
										<div class="text-center">
											<h2 class="text-center _600">${reportLength}</h2>
											<p class="text-muted m-b-md">Builds</p>
											<div>
												<span data-ui-jp="sparkline" data-ui-options="[2,3,2,2,1,3,6,3,2,1], {type:'line', height:20, width: '60', lineWidth:1, valueSpots:{'0:':'#818a91'}, lineColor:'#818a91', spotColor:'#818a91', fillColor:'', highlightLineColor:'rgba(120,130,140,0.3)', spotRadius:0}" class="sparkline inline"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xs-6 col-sm-3 b-r b-b">
									<div class="padding">
										<div>
											<span class="pull-right"><i class="fa fa-caret-up text-primary m-y-xs"></i></span>
											<span class="text-muted l-h-1x"><i class="ion-document text-muted"></i></span>
										</div>
										<div class="text-center">
											<h2 class="text-center _600">${testLength}</h2>
											<p class="text-muted m-b-md">Tests</p>
											<div>
												<span data-ui-jp="sparkline" data-ui-options="[1,1,0,2,3,4,2,1,2,2], {type:'line', height:20, width: '60', lineWidth:1, valueSpots:{'0:':'#818a91'}, lineColor:'#818a91', spotColor:'#818a91', fillColor:'', highlightLineColor:'rgba(120,130,140,0.3)', spotRadius:0}" class="sparkline inline"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xs-6 col-sm-3 b-r b-b">
									<div class="padding">
										<div>
											<span class="pull-right"><i class="fa fa-caret-down text-danger m-y-xs"></i></span>
											<span class="text-muted l-h-1x"><i class="ion-pie-graph text-muted"></i></span>
										</div>
										<div class="text-center">
											<h2 class="text-center _600">${reportTodayLength}</h2>
											<p class="text-muted m-b-md">Builds Today</p>
											<div>
												<span data-ui-jp="sparkline" data-ui-options="[9,2,5,5,7,4,4,3,2,2], {type:'line', height:20, width: '60', lineWidth:1, valueSpots:{'0:':'#818a91'}, lineColor:'#818a91', spotColor:'#818a91', fillColor:'', highlightLineColor:'rgba(120,130,140,0.3)', spotRadius:0}" class="sparkline inline"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xs-6 col-sm-3 b-b">
									<div class="padding">
										<div>
											<span class="pull-right"><i class="fa fa-caret-up text-primary m-y-xs"></i></span>
											<span class="text-muted l-h-1x"><i class="ion-paper-airplane text-muted"></i></span>
										</div>
										<div class="text-center">
											<h2 class="text-center _600">${testTodayLength}</h2>
											<p class="text-muted m-b-md">Tests Today	</p>
											<div>
												<span data-ui-jp="sparkline" data-ui-options="[3,3,1,62,4,3,7,3,2,5], {type:'line', height:20, width: '60', lineWidth:1, valueSpots:{'0:':'#818a91'}, lineColor:'#818a91', spotColor:'#818a91', fillColor:'', highlightLineColor:'rgba(120,130,140,0.3)', spotRadius:0}" class="sparkline inline"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="padding">
								<div class="row">
									<div class="col-sm-8">
										<div class="box">
											<div class="box-header">
												<h3>Build Duration <span class="label warn">seconds</h3>
												<small>Build performance (showing last 10 only)</small>
											</div>
											<!-- <div class="box-tool">
												<ul class="nav">
													<li class="nav-item inline dropdown">
														<a data-toggle="dropdown" class="btn btn-xs rounded white dropdown-toggle">Today</a>
														<div class="dropdown-menu pull-right">
															<a class="dropdown-item" href="#">Last 24 hours</a>
															<a class="dropdown-item" href="#">Last 7 days</a>
															<a class="dropdown-item" href="#">Last 14 days</a>
															<a class="dropdown-item" href="#">Last month</a>
															<div class="dropdown-divider"></div>
															<a class="dropdown-item">Today</a>
														</div>
													</li>
												</ul>
											</div> -->
											<div class="box-body">
												<div id="build-duration-vs-count-container" class="box-body">
													<canvas id="build-duration-vs-count" 
														width="{{buildDurationVsTotalsContainerWidth}}" 
														height="325" 
														class="chart chart-bar" 
														chart-data="buildDurationVsTotalsData" 
														chart-labels=buildDurationVsTotalsLabels 
														chart-options="buildDurationVsTotalsOptions" 
														chart-dataset-override="buildDurationVsTotalsDatasetOverride">
													</canvas>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="box">
											<div class="box-header">
												<h3>Top Failed</h3>
											</div>
											<div class="box-body">
												<table class="table">
													<thead>
														<tr>
															<th>Name</th>
															<th>Count</th>
														</tr>
													</thead>
													<tbody>
														<#list topFailedList as test>
														<#if test?counter <= 8>
														<tr>
															<td>${test.name}</td>
															<td class="text-danger">
																<span class="label rounded deep-orange">${test.total}</span>
															</td>
														</tr>
														</#if>
														</#list>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="box">
											<div class="box-header">
												<h3>Builds with Total Counts and Status Breakdown</h3>
												<small>A general overview of your run sessions (showing last 10 only)</small>
											</div>
											<!-- <div class="box-tool">
												<ul class="nav">
													<li class="nav-item inline dropdown">
														<a data-toggle="dropdown" class="btn btn-xs rounded white dropdown-toggle">Today</a>
														<div class="dropdown-menu pull-right">
															<a class="dropdown-item" href="#">Last 24 hours</a>
															<a class="dropdown-item" href="#">Last 7 days</a>
															<a class="dropdown-item" href="#">Last 14 days</a>
															<a class="dropdown-item" href="#">Last month</a>
															<div class="dropdown-divider"></div>
															<a class="dropdown-item">Today</a>
														</div>
													</li>
												</ul>
											</div> -->
											<div id="build-status-container" class="box-body">
												<canvas id='test-trends' 
													width="{{width}}" 
													height="325" 
													class='chart chart-bar' 
													chart-dataset-override="buildStatusDatasetOverride" 
													chart-data="buildStatusData" 
													chart-labels="buildStatusLabels" 
													chart-legend="true" 
													chart-series="series" 
													chart-options="analysisOptions"></canvas>
											</div>
										</div>
									</div>
								</div>
								
								<div class="box">
									<div class="box-header b-b">
										<h3>Build Periodic Status (Showing last {{daysPast}} days)</h3>
									</div>
									<div class="box-tool">
										<div class="dropdown">
											<a data-toggle="dropdown" class="btn btn-xs rounded white dropdown-toggle">Select Period</a>
											<div class="dropdown-menu pull-right">
												<a class="dropdown-item" href="#" ng-click="getPeriodReportAggregation(1)">Last 24 hours</a>
												<a class="dropdown-item" href="#" ng-click="getPeriodReportAggregation(7)">Last 7 days</a>
												<a class="dropdown-item" href="#" ng-click="getPeriodReportAggregation(14)">Last 14 days</a>
												<a class="dropdown-item" href="#" ng-click="getPeriodReportAggregation(21)">Last 21 days</a>
												<a class="dropdown-item" href="#" ng-click="getPeriodReportAggregation(30)">Last 30 days</a>
												<div class="dropdown-divider"></div>
												<a class="dropdown-item">Today</a>
											</div>
										</div>
									</div>
									<div>
										<div class="row-col">
											<div class="col-sm-3 b-r light lt">
												<div class="p-a-md">
													<#list weeklyList as weekly>
													<div class="list-group list-group-gap m-b">
														<div class="list-group-item">
															<span class="pull-right label">${weekly.total} tests</span>
															<small>${weekly.startTime?date}</small>
														</div>
													</div>
													</#list>
												</div>
											</div>
											<div class="col-sm-9" ng-init="getPeriodReportAggregation(30)">
												<div class="p-a">
													<canvas id="base" class="chart-bar"
														chart-data="periodReportAggregationData"
														chart-labels="periodReportAggregationLabels" 
														chart-options="periodReportAggregationOptions"
														chart-dataset-override="periodReportAggregationOverride">
													</canvas> 
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="box">
											<div class="box-header">
												<span class="label success pull-right">${categoryList?size}</span>
												<h3>Categories</h3>
											</div>
											<div class="p-b-sm">
												<ul class="list no-border m-a-0">
													<#assign colors=['danger','purple','info','warning','success','indigo','teal','lime','pink','deep-purple']>
													<#assign cntr=0>
													<#list categoryList as category>
													<#if (cntr >= 9)>
													<#assign cntr=0>
													</#if>
													<li class="list-item">
														<a href="#" class="list-left">
														<span class="w-40 avatar ${colors[cntr]}">
														<span>${category.name[0]}</span>
														<i class="on b-white bottom"></i>
														</span>
														</a>
														<div class="list-body">
															<div><a href="#">${category.name}</a></div>
															<small class="text-muted text-ellipsis">${category.total} tests</small>
														</div>
													</li>
													<#assign cntr++>
													</#list>
												</ul>
											</div>
										</div>
									</div>
									<#if authorList?size != 0>
									<div class="col-sm-6">
										<div class="box">
											<div class="box-header">
												<span class="label success pull-right">${authorList?size}</span>
												<h3>Authors</h3>
											</div>
											<div class="p-b-sm">
												<ul class="list no-border m-a-0">
													<#assign colors=['danger','purple','info','warning','success','indigo','teal','lime','pink','deep-purple']>
													<#assign cntr=0>
													<#list authorList as author>
													<#if (cntr >= 9)>
													<#assign cntr=0>
													</#if>
													<li class="list-item">
														<a href="#" class="list-left">
														<span class="w-40 avatar ${colors?reverse[cntr]}">
														<span>${author.name[0]}</span>
														<i class="on b-white bottom"></i>
														</span>
														</a>
														<div class="list-body">
															<div><a href="#">${author.name}</a></div>
															<small class="text-muted text-ellipsis">${author.total} tests</small>
														</div>
													</li>
													<#assign cntr++>
													</#list>
												</ul>
											</div>
										</div>
									</div>
									</#if>
								</div>
							</div>
						</div>
						<div class="col-lg w-lg w-auto-md white bg">
							<div>
								<div class="p-a">
									<h6 class="text-muted m-a-0">Activity</h6>
								</div>
								<div class="streamline">
									<#list testList as test>
									<div class="sl-item b-${Color.getBootstrapKeyword(test.status)}">
										<div class="sl-content">
											<div alt="${test.status}" title="${test.status}">
												<a href="/test?id=${test.id}">${test.name}</a>
											</div>
											<span class="sl-date text-muted">${prettyTime.format(test.startTime)}</span>
										</div>
									</div>
									</#list>
								</div>
							</div>
						</div>
					</div>
					<!-- ############ PAGE END-->
				</div>
			</div>
			<!-- / -->
			<#include 'partials/switcher.ftl'>
		</div>
		<#include 'partials/scripts.ftl'>
		<#include 'partials/angular.ftl'>
	</body>
</html>