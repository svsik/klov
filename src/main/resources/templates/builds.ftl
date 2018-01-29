<#assign Math=statics['java.lang.Math']>
<!DOCTYPE html>
<html lang="en" ng-app="Klov">
    <#include 'partials/head.ftl'>
    <style type="text/css">
        .mt-70 {
         margin-top: 70px !important;
         }
         .badge-primary {
         background-color: #33cabb;
         }
         .badge-info {
         background-color: #48b0f7;
         }
         .badge-yellow {
         background-color: #fcc525;
         }
         .badge-danger {
         background-color: #f96868
         }
         .badge {
         border-radius: 3px;
         font-weight: 400;
         line-height: 1.3;
         font-size: 85%;
         }
         .ml-2 {
         margin-left: .5rem!important;
         }
         .badge-primary {
         color: #fff;
         background-color: #007bff;
         }
         .badge {
         display: inline-block;
         padding: .25em .4em;
         font-size: 75%;
         font-weight: 700;
         line-height: 1;
         color: #fff;
         text-align: center;
         white-space: nowrap;
         vertical-align: baseline;
         border-radius: .25rem;
         }
         .badge:empty {
         display: inline-block;
         vertical-align: inherit;
         }
         .badge-ring {
         position: relative;
         width: 10px;
         height: 10px;
         padding: 0;
         border-radius: 100%;
         vertical-align: middle;
         }
         .badge-ring::after {
         content: '';
         position: absolute;
         top: 2px;
         left: 2px;
         width: 6px;
         height: 6px;
         border-radius: 50%;
         background-color: #fff;
         -webkit-transform: scale(1);
         transform: scale(1);
         -webkit-transition: .3s;
         transition: .3s;
         }
         .align-self-end {
         position:absolute;
         bottom:0;
         right:25px;
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
                        <i class="material-icons">reorder</i>
                        </a>
                        <div class="navbar-item pull-left h5" id="pageTitle">Builds</div>
                        <#include 'partials/navbar-right.ftl'>
                    </div>
                </div>
                <#include 'partials/footer.ftl'>
                <div class="app-body">
                    <!-- ############ PAGE START-->
                    <div class="padding">
                        <div class="row m-b">
                            <#list reportList as report> <#if (report.name)?? && (report.parentLength)??>
	                            <#if report.parentLength != 0>
		                            <#assign featurePassed=Math.round((report.passParentLength/report.parentLength)*100)>
		                            <#assign featureFailed=Math.round(((report.failParentLength+report.fatalParentLength+report.errorParentLength)/report.parentLength)*100)>
		                            <#assign featureOthers=Math.round(((report.skipParentLength+report.warningParentLength)/report.parentLength)*100)>
		                            <#assign featureScore=Math.round((report.passParentLength/report.parentLength)*100)>
	                            </#if>
	                            <#if report.childLength != 0> 
	                            	<#assign scenarioScore=Math.round((report.passChildLength/report.childLength)*100)> 
	                            </#if>
	                            <#if report.grandChildLength != 0> 
	                            	<#assign stepScore=Math.round((report.passGrandChildLength/report.grandChildLength)*100)> 
	                            </#if>
	                            
	                            <div class="col-sm-4" ng-if="!r${report?counter}">
	                                <div class="box">
	                                    <div class="box-header">
	                                        <h3>${report.name}</h3>
	                                        <small>${prettyTime.format(report.startTime)}</small>
	                                        <small>${report.startTime?datetime}</small>
	                                    </div>
	                                    <div class="box-tool">
	                                        <ul class="nav">
	                                            <li class="nav-item inline">
	                                                <a class="nav-link" alt="View all tests" title="View all tests" href="/build?id=${report.id}">
	                                                <button class="btn btn-icon white"><i class="material-icons">input</i></button>
	                                                </a>
	                                            </li>
	                                            <li class="nav-item inline">
	                                                <a class="nav-link" alt="View failed tests" title="View failed tests" href="/build?id=${report.id}&status=fail">
	                                                <button class="btn btn-icon white"><i class="material-icons">report</i></button>
	                                                </a>
	                                            </li>
	                                            <!-- if admin -->
	                                            <#if user?? && user.admin>
	                                            <li class="nav-item inline">
	                                                <a class="nav-link" ng-click="remove('${report.id}', 'r${report?counter}')" href="#" alt="Delete report" title="Delete report">
	                                                <button class="btn btn-icon white"><i class="material-icons">delete</i></button>
	                                                </a>
	                                            </li>
	                                            </#if>
	                                        </ul>
	                                    </div>
	                                    <div class="box-body">
	                                        <p class="m-a-0">
                                        		${report.name} executed for ${report.parentLength} 
                                        		<#if isBDD>
                                        		features, ${report.childLength} scenarios and ${report.grandChildLength} steps. 
                                        		<#else>
                                        		tests.
                                        		</#if>
                                        		Out of ${report.parentLength} 
                                        		<#if isBDD>
                                        		features,  
                                        		<#else>
                                        		tests, 
                                        		</#if>
                                        		${report.passParentLength} passed. This build has a success percentage of ${featurePassed}%.
	                                     	</p>
	                                        <#if report.parentLength != 0>
	                                        <div class="mt-70">
	                                            <div class="peity-chart" data-provide="peity" data-type="bar" data-height="130" data-width="90" data-fill="#33cabb,#48b0f7,#fdd501">${featurePassed},${featureFailed},${featureOthers}</div>
	                                            <ul class="pull-right list-inline align-self-end text-muted text-right mb-0">
	                                                <li>${featurePassed}% Pass &nbsp; <span class="badge badge-ring badge-primary ml-2"></span></li>
	                                                <li>${featureFailed}% Failed &nbsp; <span class="badge badge-ring badge-danger ml-2"></span></li>
	                                                <li>${featureOthers}% Others &nbsp; <span class="badge badge-ring badge-yellow ml-2"></span></li>
	                                            </ul>
	                                        </div>
	                                        </#if>
	                                    </div>
	                                </div>
	                            </div>
                            </#if></#list>
                        </div>
                        <ul class="pagination">
                            <!-- previous, next -->
                            <#assign disabled="">
                            <#if page==0><#assign disabled="disabled"></#if>
                            <li class="page-item ${disabled}">
                                <a class="page-link" href="/builds?page=0" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <#list 0..pages-1 as x>
                            <li class="page-item"><a class="page-link" href="/builds?page=${x}">${x+1}</a></li>
                            </#list>
                            <#assign disabled="">
                            <#if page==pages-1><#assign disabled="disabled"></#if>
                            <li class="page-item ${disabled}">
                                <a class="page-link" href="/builds?page=${pages-1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- / -->
            <#include 'partials/switcher.ftl'>
            <!-- ############ LAYOUT END-->
        </div>
        <#include 'partials/scripts.ftl'>
        <#include 'partials/angular.ftl'>
        <script>
            $(".peity-chart").peity("bar", {
            	height: 130,
            	width: 90,
            	fill: [ "#33cabb" ,"#f96868", "#fdd501" ]
            });
        </script>
    </body>
</html>