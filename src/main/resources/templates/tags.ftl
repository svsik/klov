<!DOCTYPE html>
<html lang="en" ng-app="Klov">
    <#include 'partials/head.ftl'>
    <style>
        .border-box > h6 {
        margin-bottom: 20px;
        }
        .border-box {
        border: 1px solid rgba(120,130,140,.2);
        border-radius: .25rem;
        }
        .node.pass {
        background-color: #aed581;
        }
        .node.skip, .node.warning {
        background-color: #ff7043;
        }
        .node.fail, .node.error, .node.fatal {
        background-color: #e57373;
        }
        .bdd-node, .bdd-node h6, .bdd-node p, .bdd-node span:not(.label) {
        font-size: .9rem !important;
        }
        td.status-cell {
        width: 75px;
        }
        th {
        font-size: .75rem;
        }
        .table > thead > tr > th:first-child, .table > tbody > tr > td:first-child {
        padding-left: 0;
        }
        .sl-item {
        padding-top: 10px;		
        }
    </style>
    <body>
        <div class="app report-page" id="app">
            <#include 'partials/sidenav.ftl'>
            <!-- content -->
            <div id="content" class="app-content box-shadow-z2 bg pjax-container" role="main" ng-controller="CategoryController">
                <div class="app-body">
                    <!-- ############ PAGE START-->
                    <div class="app-body-inner">
                        <div class="row-col">
                            <div class="col-xs-3 modal fade aside aside-lg" id="subnav">
                                <div class="row-col black b-r bg">
                                    <div class="b-b">
                                        <div class="navbar no-radius">
                                            <!-- nabar right -->
                                            <ul class="nav navbar-nav pull-right m-l">
                                                <li class="nav-item dropdown">
                                                    <a class="nav-link" data-toggle="dropdown">
                                                    <span class="btn btn-xs white rounded dropdown-toggle">
                                                    New
                                                    </span>
                                                    </a>
                                                    <div class="dropdown-menu text-color pull-right" role="menu">
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-inbox"></i>
                                                        New project
                                                        </a>
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-check-square-o"></i>
                                                        New task
                                                        </a>
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-file-o"></i>
                                                        New note
                                                        </a>
                                                        <div class="dropdown-divider"></div>
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-comment-o"></i>
                                                        Message
                                                        </a>
                                                    </div>
                                                </li>
                                            </ul>
                                            <!-- / navbar right -->
                                            <!-- link and dropdown -->
                                            <ul class="nav navbar-nav">
                                                <li class="nav-item">
                                                    <span class="navbar-item text-md">Categories</span>
                                                </li>
                                            </ul>
                                            <!-- / link and dropdown -->
                                        </div>
                                    </div>
                                    <!-- flex content -->
                                    <div class="row-row">
                                        <div class="row-body scrollable hover">
                                            <div class="row-inner">
                                                <!-- left content -->
                                                <div class="list" data-ui-list="b-r b-2x b-theme">
                                                    <#list categoryList as category>
                                                    <div class="list-item ">
                                                        <div class="list-body">
                                                            <div class="pull-right dropdown">
                                                                <a href="#" data-toggle="dropdown" class="text-muted"><i class="fa fa-fw fa-ellipsis-h"></i></a>
                                                                <div class="dropdown-menu pull-right text-color" role="menu">
                                                                    <a class="dropdown-item">
                                                                    <i class="fa fa-tag"></i>
                                                                    Tag item
                                                                    </a>
                                                                    <a class="dropdown-item">
                                                                    <i class="fa fa-pencil"></i>
                                                                    Edit item
                                                                    </a>
                                                                    <a class="dropdown-item">
                                                                    <i class="fa fa-trash"></i>
                                                                    Delete item
                                                                    </a>
                                                                    <div class="dropdown-divider"></div>
                                                                    <a class="dropdown-item">
                                                                    <i class="fa fa-ellipsis-h"></i>
                                                                    More action
                                                                    </a>
                                                                </div>
                                                            </div>
                                                            <div class="item-title">
                                                                <a href="#" class="_500" ng-click="findReportsByCategoryName('${category.name}')">${category.name}</a>
                                                            </div>
                                                            <small class="block text-ellipsis">
                                                            <span class="text-xs">
                                                            50
                                                            </span> <span class="text-muted">completed</span>
                                                            </small>
                                                            <div class="progress-xxs m-y-sm lter progress w-sm">
                                                                <div class="progress-bar success" data-toggle="tooltip" title="Finished: 44%" style="width: 19%;">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </#list>
                                                </div>
                                                <!-- / -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- / -->
                                    <!-- footer -->
                                    <div class="p-a b-t clearfix">
                                        <div class="btn-group pull-right">
                                            <a href="#" class="btn btn-xs white circle"><i class="fa fa-fw fa-angle-left"></i></a>
                                            <a href="#" class="btn btn-xs white circle"><i class="fa fa-fw fa-angle-right"></i></a>
                                        </div>
                                        <span class="text-sm text-muted">Total: <strong>4</strong></span>
                                    </div>
                                    <!-- / -->
                                </div>
                            </div>
                            <div class="col-xs-3 modal fade aside aside-lg" id="list">
                                <div class="row-col b-r light lt">
                                    <div class="b-b">
                                        <div class="navbar no-radius">
                                            <a data-toggle="modal" data-target="#subnav" data-ui-modal class="navbar-item pull-left hidden-xl-up hidden-sm-down">
                                            <span class="btn btn-sm btn-icon blue">
                                            <i class="fa fa-th"></i>
                                            </span>
                                            </a>
                                            <!-- nabar right -->
                                            <ul class="nav navbar-nav pull-right m-l">
                                                <li class="nav-item dropdown">
                                                    <a class="nav-link text-muted" href="#" data-toggle="dropdown">
                                                    <i class="fa fa-ellipsis-h"></i>
                                                    </a>
                                                    <div class="dropdown-menu pull-right text-color" role="menu">
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-tag"></i>
                                                        Tag item
                                                        </a>
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-pencil"></i>
                                                        Edit item
                                                        </a>
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-trash"></i>
                                                        Delete item
                                                        </a>
                                                        <div class="dropdown-divider"></div>
                                                        <a class="dropdown-item">
                                                        <i class="fa fa-ellipsis-h"></i>
                                                        More action
                                                        </a>
                                                    </div>
                                                </li>
                                            </ul>
                                            <!-- / navbar right -->
                                            <!-- link and dropdown -->
                                            <ul class="nav navbar-nav">
                                                <li class="nav-item">
                                                    <span class="navbar-item m-r-0 text-md">Builds</span>
                                                </li>
                                                <li class="nav-item" ng-if="reportList">
                                                    <a class="nav-link">
                                                    <span class="label rounded">
                                                    {{reportList.length}}
                                                    </span>
                                                    </a>
                                                </li>
                                            </ul>
                                            <!-- / link and dropdown -->
                                        </div>
                                    </div>
                                    <!-- flex content -->
                                    <div class="row-row">
                                        <div class="row-body scrollable hover">
                                            <div class="row-inner">
                                                <!-- content -->
                                                <div class="list" data-ui-list="b-r b-2x b-theme" ng-if="reportList">
                                                    <div class="list-item row-col" ng-repeat="report in reportList">
                                                        <div class="list-body col-xs" ng-click="findTestsByReportIdForActiveCategory(report.id)">
                                                            <a href="#" class="item-title _500">{{report.name}}</a>
                                                            <div class="text-muted text-xs">
                                                                <i class="fa fa-clock-o"></i> {{report.startTime | date: 'MM/dd/yyyy HH:mm:ss'}} | {{report.duration/1000}} seconds
                                                            </div>
                                                            <div class="dropdown m-t-xs">
                                                                <a href="#" data-toggle="dropdown">
                                                                
                                                                </a>
                                                                <div class="dropdown-menu">
                                                                    <a class="dropdown-item" href="#">
                                                                    <i class="fa fa-circle-o text-accent"></i>Active
                                                                    </a>
                                                                    <a class="dropdown-item" href="#">
                                                                    <i class="fa fa-circle-o text-warn"></i>In progress
                                                                    </a>
                                                                    <a class="dropdown-item" href="#">
                                                                    <i class="fa fa-circle-o text-success"></i>Completed
                                                                    </a>
                                                                    <a class="dropdown-item" href="#">
                                                                    <i class="fa fa-circle-o text-muted"></i>Archived
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- / -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- / -->
                                    <!-- footer -->
                                    <div class="p-a b-t clearfix">
                                        <div class="btn-group pull-right">
                                            <a href="#" class="btn btn-xs white circle"><i class="fa fa-fw fa-angle-left"></i></a>
                                            <a href="#" class="btn btn-xs white circle"><i class="fa fa-fw fa-angle-right"></i></a>
                                        </div>
                                        <span class="text-sm text-muted">Completed: <strong>10</strong>, In Progress: <strong>5</strong></span>
                                    </div>
                                    <!-- / -->
                                </div>
                            </div>
                            <div class="col-xs-5" id="detail">
                                <div class="row-col white b-r">
                                    <div class="b-b">
                                        <div class="navbar no-radius">
                                            <!-- nabar right -->
                                            <ul class="nav navbar-nav pull-right m-l">
                                                <li class="nav-item dropdown">
                                                    <a class="nav-link">
                                                    <span class="label warn rounded">
                                                    24
                                                    </span>
                                                    </a>
                                                </li>
                                            </ul>
                                            <!-- / navbar right -->
                                            <a data-toggle="modal" data-target="#subnav" data-ui-modal class="navbar-item pull-left hidden-md-up">
                                            <span class="btn btn-sm btn-icon blue">
                                            <i class="fa fa-th"></i>
                                            </span>
                                            </a>
                                            <a data-toggle="modal" data-target="#list" data-ui-modal class="navbar-item pull-left hidden-md-up">
                                            <span class="btn btn-sm btn-icon btn-default">
                                            <i class="fa fa-list"></i>
                                            </span>
                                            </a>
                                            <span class="navbar-item text-md text-ellipsis">Tests</span>
                                        </div>
                                    </div>
                                    <!-- flex content -->
                                    <div class="row-row">
                                        <div class="row-body scrollable hover">
                                            <div class="row-inner">
                                                <!-- content -->
                                                
												<div class="p-a" ng-if="testList && bdd" ng-repeat="currentTest in testList">
													<!-- PARENT -->
													<h4><small class="_600" ng-if="currentTest.bddType">{{currentTest.bddType}}:</small> {{currentTest.name}}</h4>
													<span class="label teal">{{currentTest.startTime | date: 'MMM dd, yyyy hh:mm:ss'}}</span>
													<span class="label brown">{{currentTest.endTime | date: 'MMM dd, yyyy hh:mm:ss'}}</span>
													<span class="label {{getColor(currentTest.status)}}">{{currentTest.status}}</span>
													
													<br/><br/>
													
													<!-- parent nodes -->
													<div class="bdd-node" ng-if="currentTest.nodes.length">
														<!-- node1 -->
														<div class="p-a box light border-box" ng-repeat="node1 in currentTest.nodes">
															<p ng-if="node1.categorized">
																<span class="label blue-grey" ng-repeat="category in node1.categoryNameList"><i class="fa fa-tag"></i> &nbsp; {{category}}</span>
															</p>
														
															<h6><small class="_600" ng-if="node1.bddType">{{node1.bddType}}:</small> {{node1.name}}</h6>
															
															<!-- node1 logs -->
															<div class="list-group m-b log" ng-if="node1.logs" ng-repeat="log in node1.logs">
																<p class="list-group-item b-l-{{getBootstrapColor(log.status)}}" 
																	ng-bind-html="trust(log.details)">
																</p>
																<p class="list-group-item b-l-{{getBootstrapColor(log.status)}}" ng-if="log.media" ng-repeat="m in log.media"> 
																	<a href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
																		<span class="label info">img</span>
																	</a>
																</p>
															</div>
															
															<!-- node1 media -->
															<div class="row">
																<div class="col-sm-4 media" ng-if="node1.media" ng-repeat="m in node1.media">
																	<div ng-if="m.base64String" class="box p-a">
																		<a href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
																			<img class="img-responsive" src="data:image/png;base64,{{m.base64String}}">
																		</a>
																	</div>
																</div>
															</div>
															
															<!-- node1 nodes -->
															<div ng-if="node1.nodes.length">
																<!-- nodes -->
																<div ng-repeat="node2 in node1.nodes" ng-class="node2.status" class="node p-a">
																	<h6><small class="_600" ng-if="node2.bddType">{{node2.bddType}}:</small> {{node2.name}}</h6>
																	
																	<!-- node2 logs -->
																	<div class="log" ng-if="node2.logs" ng-repeat="log in node2.logs">
																		<span ng-bind-html="trust(log.details)"></span>
																		<a ng-if="log.media" ng-repeat="m in log.media" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
																			<span class="label">
																				<i class="fa fa-image"></i>
																			</span>
																		</a>
																	</div>

																	<!-- node2 media -->
																	<div ng-if="node2.media && !m.log" ng-repeat="m in node2.media" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
																		<span class="label">
																			<i class="fa fa-image"></i>																		
																		</span>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											
												<div class="p-a" ng-if="testList && !bdd" ng-repeat="currentTest in testList">
													<!-- PARENT -->
													<h4><small class="_600">{{currentTest.name}}</small></h4>
													<span class="label teal">{{currentTest.startTime | date: 'MMM dd, yyyy hh:mm:ss'}}</span>
													<span class="label brown">{{currentTest.endTime | date: 'MMM dd, yyyy hh:mm:ss'}}</span>
													<span class="label {{getColor(currentTest.status)}}">{{currentTest.status}}</span>
													
													<br/>
													<br/>
													
													<div class="streamline" ng-if="currentTest.logs">
												        <div ng-repeat="log in currentTest.logs" class="sl-item b-{{getBootstrapColor(log.status)}}">
												          <div class="sl-icon">
												            <i class="fa fa-{{getFont(log.status)}}"></i>
												          </div>
												          <div class="sl-content">
												            <div class="sl-date text-muted">{{getTime(log.timestamp)}}</div>
												            <div ng-bind-html="trust(log.details)"></div>
												            <a ng-if="log.media" ng-repeat="m in log.media" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
																<span class="label">img</span>
															</a>
															<div>&nbsp;</div>
												          </div>
												        </div>
												    </div>
																	
													<!-- parent media -->
													<a ng-if="currentTest.media" ng-repeat="m in currentTest.media" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
														<span class="label">img</span>
													</a>
													
													<!-- parent nodes -->
													<div class="" ng-if="currentTest.nodes.length">
														<!-- node1 -->
														<div class="p-a box border-box light" ng-repeat="node1 in currentTest.nodes">
															<span class="label {{getColor(node1.status)}} pull-right">{{node1.status}}</span>
															<h6 class="_600">{{node1.name}}</h6>
															
															<!-- node1 logs -->
															<div class="streamline" ng-if="node1.logs">
														        <div ng-repeat="log in node1.logs" class="sl-item b-{{getBootstrapColor(log.status)}}">
														          <div class="sl-icon">
														            <i class="fa fa-{{getFont(log.status)}}"></i>
														          </div>
														          <div class="sl-content">
														            <div class="sl-date text-muted">{{getTime(log.timestamp)}}</div>
														            <div ng-bind-html="trust(log.details)"></div>
														            <a ng-if="log.media" ng-repeat="m in log.media" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
																		<span class="label">img</span>
																	</a>
														          </div>
														        </div>
														    </div>
															
															<!-- node1 media -->
															<span class="label" ng-if="node1.media" ng-repeat="m in node1.media" style="margin-right: 5px;">
																<i class="fa fa-image"></i>
																<a ng-if="m.base64String" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image"></a>
															</span>
															
															<!-- node1 nodes -->
															<div class="p-a" ng-if="node1.nodes.length">
																<!-- nodes -->
																<div ng-repeat="node2 in node1.nodes">
																	<span class="label {{getColor(node2.status)}} pull-right">{{node2.status}}</span>
																	<h6 class="_600">{{node2.name}}</h6>
																	
																	<!-- node2 logs -->
																	<div class="streamline" ng-if="node2.logs">
																        <div ng-repeat="log in node2.logs" class="sl-item b-{{getBootstrapColor(log.status)}}">
																          <div class="sl-icon">
																            <i class="fa fa-{{getFont(log.status)}}"></i>
																          </div>
																          <div class="sl-content">
																            <div class="sl-date text-muted">{{getTime(log.timestamp)}}</div>
																            <div ng-bind-html="trust(log.details)"></div>
																            <a ng-if="log.media" ng-repeat="m in log.media" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image">
																				<span class="label">img</span>
																			</a>
																			<div>&nbsp;</div>
																          </div>
																        </div>
																    </div>
																	
																	<!-- node2 media -->
																	<span class="label" ng-if="node2.media" ng-repeat="m in node2.media" style="margin-right: 5px;">
																		<i class="fa fa-image"></i>
																		<a ng-if="m.base64String" href="data:image/png;base64,{{m.base64String}}" data-featherlight="image"></a>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
												
                                                <!-- / -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- footer -->
                                    <div class="p-a p-y-sm b-t">
                                        <form>
                                            <div class="input-group b-a b-transparent">
                                                <input type="text" class="form-control no-border" placeholder="What are you doing">
                                                <span class="input-group-btn">
                                                <button class="btn no-bg no-shadow" type="button">
                                                <i class="fa fa-send text-success"></i>
                                                </button>
                                                </span>
                                            </div>
                                        </form>
                                    </div>
                                    <!--  -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ############ PAGE END-->
                </div>
            </div>
            <!-- / -->
            <#include 'partials/switcher.ftl'>
            <!-- ############ LAYOUT END-->
        </div>
        <#include 'partials/scripts.ftl'>
        <#include 'partials/angular.ftl'>
    </body>
</html>