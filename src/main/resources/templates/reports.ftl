<#assign Math=statics['java.lang.Math']>

<!DOCTYPE html>
<html lang="en" ng-app="Klov">
    <#include 'partials/head.ftl'>
    
    <style type="text/css">
        table th, table td {
        	vertical-align: middle !important;
        	border-bottom: 1px solid rgba(120, 130, 140, 0.12) !important;
        }
        td.report-name {
        	width: 35%;
        }
        td.report-categories {
        	width: 20%;
        }
        .easyPieChart {
        	float: left;
        }
    </style>
    
    <body>
        <div class="app" id="app">
            <#include 'partials/sidenav.ftl'>
            <!-- content -->
            <div id="content" class="app-content box-shadow-z2 bg pjax-container" role="main" ng-controller="ReportController">
                <div class="app-header white bg b-b">
                    <div class="navbar" data-pjax>
                        <a data-toggle="modal" data-target="#aside" class="navbar-item pull-left hidden-lg-up p-r m-a-0">
                        <i class="ion-navicon"></i>
                        </a>
                        <div class="navbar-item pull-left h5" id="pageTitle">Builds</div>
                        <#include 'partials/navbar-right.ftl'>
                    </div>
                </div>
                <#include 'partials/footer.ftl'>
                <div class="app-body">
                    <!-- ############ PAGE START-->
                    <div class="padding">
                        <div class="box">
                            <div class="box-header">
                                <h2>Builds</h2>
                                <small>Quick summary of builds</small>
                            </div>
                            <br/>
                            <div>
                                <table class="table m-b-none">
                                    <thead>
                                        <tr>
                                            <th class="report-name">Build</th>
                                            <th class="time">When</th>
                                            <th class="time">Duration</th>
                                            <#if isBDD>
                                            	<th>Feature</th>
                                            	<th>Scenario</th>
                                            	<th>Step</th>
                                            <#else>
                                            	<th>Tests</th>
                                            	<th>Failed</th>
                                            	<th>Passed %</th>
                                            </#if>
                                            <th>Categories</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <#list reportList as report> <#if (report.name)?? && (report.parentLength)??>
	                                        <#if report.parentLength != 0>
	                                        	<#assign featureScore=Math.round((report.passParentLength/report.parentLength)*100)>
	                                        	
	                                        	<#if featureScore == 100>
	                                        		<#assign featureColor="#4caf50">
	                                        	<#else>
	                                        		<#assign featureScore=Math.round(((report.passParentLength)/report.parentLength)*100)>
	                                        		<#assign featureColor="red">
	                                        	</#if>
	                                        </#if>
	                                        
	                                        <#if report.childLength != 0> 
	                                        	<#assign scenarioScore=Math.round((report.passChildLength/report.childLength)*100)> 
	                                        </#if>
	                                        
	                                        <#if report.grandChildLength != 0> 
	                                        	<#assign stepScore=Math.round((report.passGrandChildLength/report.grandChildLength)*100)> 
	                                        </#if>
	                                        
	                                        <tr ng-if="!r${report?counter}">
                                                <td class="report-name">
	                                                <a href="/report?id=${report.id}">${report.name}</a>
	                                                <br/>
	                                            	<small class="text-muted">${report.startTime?datetime}</small>
	                                            </td>
	                                            <td class="time">${prettyTime.format(report.startTime)}</td>
	                                            <td class="time"><span class="text-muted">${report.duration/60}s</span></td>
	                                            <#if isBDD>
		                                            <td>
		                                            	<#if report.parentLength != 0>
		                                                <div data-ui-jp="easyPieChart" class="easyPieChart" data-redraw='true' data-percent="${featureScore}" data-ui-options="{
		                                                    lineWidth: 5,
		                                                    trackColor: '#fff',
		                                                    barColor: '#e5e9f0',
		                                                    scaleColor: '#fff',
		                                                    fill: '${featureColor}',
		                                                    size: 55,
		                                                    scaleLength: 0,
		                                                    lineCap: 'butt'
		                                                    }">
		                                                    <div class="small text-white">
		                                                        ${featureScore}%
		                                                    </div>
		                                                </div>
		                                                </#if>
		                                            </td>
		                                            <td>
		                                            	<#if report.childLength != 0>
		                                                <div data-ui-jp="easyPieChart" class="easyPieChart" data-redraw='true' data-percent="${scenarioScore}" data-ui-options="{
		                                                    lineWidth: 5,
		                                                    trackColor: '#fff',
		                                                    barColor: '#e5e9f0',
		                                                    scaleColor: '#fff',
		                                                    fill: '#4caf50',
		                                                    size: 55,
		                                                    scaleLength: 0,
		                                                    lineCap: 'butt'
		                                                    }">
		                                                    <div class="small text-white">
		                                                        ${scenarioScore}%
		                                                    </div>
		                                                </div>
		                                                </#if>
		                                            </td>
		                                            <td>
		                                            	<#if report.childLength != 0>
		                                                <div data-ui-jp="easyPieChart" class="easyPieChart" data-redraw='true' data-percent="${stepScore}" data-ui-options="{
		                                                    lineWidth: 5,
		                                                    trackColor: '#fff',
		                                                    barColor: '#e5e9f0',
		                                                    scaleColor: '#fff',
		                                                    fill: '#4caf50',
		                                                    size: 55,
		                                                    scaleLength: 0,
		                                                    lineCap: 'butt'
		                                                    }">
		                                                    <div class="small text-white">
		                                                        ${stepScore}%
		                                                    </div>
		                                                </div>
		                                                </#if>
		                                            </td>
		                                        <#else>
		                                        	<#if report.parentLength != 0>
		                                        	<td>${report.parentLength}</td>
		                                        	<td>${report.failParentLength+report.fatalParentLength+report.errorParentLength}</td>
		                                        	<td>
		                                                <div data-ui-jp="easyPieChart" class="easyPieChart" data-redraw='true' data-percent="${featureScore}" data-ui-options="{
		                                                    lineWidth: 5,
		                                                    trackColor: '#fff',
		                                                    barColor: '#e5e9f0',
		                                                    scaleColor: '#fff',
		                                                    fill: '${featureColor}',
		                                                    size: 55,
		                                                    scaleLength: 0,
		                                                    lineCap: 'butt'
		                                                    }">
		                                                    <div class="small text-white">
		                                                        ${featureScore}%
		                                                    </div>
		                                                </div>
		                                                </#if>
		                                            </td>
	                                            </#if>
	                                            <td class="report-categories">
	                                            	<#if report.categoryNameList??>
	                                            		<#list report.categoryNameList as category>
	                                            			<span class="label warn">${category}</span> 
	                                            		</#list>
	                                            	</#if>
	                                            </td>
	                                            <td>
	                                            	<a alt="View all tests" title="View all tests" href="/report?id=${report.id}">
	                                            		<button class="btn btn-icon white"><i class="material-icons">input</i></button>
	                                            	</a>
	                                            	<a alt="View failed tests" title="View failed tests" href="/report?id=${report.id}&status=fail">
		                                            	<button class="btn btn-icon white"><i class="material-icons">report</i></button>
	                                            	</a>
	                                            	
	                                            	<!-- if admin -->
	                                            	<#if user?? && user.admin>
	                                            		<a ng-click="remove('${report.id}', 'r${report?counter}')" href="#" alt="Delete report" title="Delete report">
			                                            	<button class="btn btn-icon white"><i class="material-icons">delete</i></button>
		                                            	</a>
	                                            	</#if>
	                                            </td>
	                                        </tr>
                                        </#if> </#list>
                                    </tbody>
                                    <tfoot class="hide-if-no-paging">
                                        <tr>
                                            <td>
                                                <ul class="pagination">
                                                    <!-- previous, next -->
                                                    <#assign disabled="">
                                                    <#if page==0><#assign disabled="disabled"></#if>
                                                    <li class="page-item ${disabled}">
                                                        <a class="page-link" href="/reports?page=0" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                        <span class="sr-only">Previous</span>
                                                        </a>
                                                    </li>
                                                    <#list 0..pages-1 as x>
                                                    <li class="page-item"><a class="page-link" href="/reports?page=${x}">${x+1}</a></li>
                                                    </#list>
                                                    <#assign disabled="">
                                                    <#if page==pages-1><#assign disabled="disabled"></#if>
                                                    <li class="page-item ${disabled}">
                                                        <a class="page-link" href="/reports?page=${pages-1}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                        <span class="sr-only">Next</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
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