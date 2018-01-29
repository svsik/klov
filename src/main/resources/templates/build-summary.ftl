<#assign Math=statics['java.lang.Math']>
<!DOCTYPE html>
<html lang="en" ng-app="Klov">
    <#include 'partials/head.ftl'>
    <style type="text/css">
    	.box-body.kl {
    		height: 200px;
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
        svg.peity {
        bottom: 20px;
        position: absolute;
        }
    </style>
    <body>
        <div class="app build-summary" id="app">
            <#include 'partials/sidenav.ftl'>
            <!-- content -->
            <div id="content" class="app-content box-shadow-z2 bg pjax-container" role="main" ng-controller="ReportController">
                <div class="app-header white bg b-b">
                    <div class="navbar" data-pjax>
                        <a data-toggle="modal" data-target="#aside" class="navbar-item pull-left hidden-lg-up p-r m-a-0">
                        <i class="material-icons">reorder</i>
                        </a>
                        <div class="navbar-item pull-left h5" id="pageTitle">Build Summary</div>
                        <#include 'partials/navbar-right.ftl'>
                    </div>
                </div>
                <#include 'partials/footer.ftl'>
                <div class="app-body">
                    <!-- ############ PAGE START-->
                    <div class="padding">
                        <div class="row m-b">
                            <div class="col-sm-4">
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
                                <div class="box">
                                    <div class="box-header">
                                        <h3><#if isBDD>Feature<#else>Test</#if> Distribution</h3>
                                    </div>
                                    <div class="box-body kl">
                                        <#if report.parentLength != 0>
                                        <div class="">
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
                            <#if isBDD>
                            <div class="col-sm-4">
                                <div class="box">
                                    <div class="box-header">
                                        <h3>Scenario Distribution</h3>
                                    </div>
                                    <div class="box-body kl">
                                        <div class="peity-chart" data-provide="peity" data-type="bar" data-height="130" data-width="90" data-fill="#33cabb,#48b0f7,#fdd501">${report.passChildLength},${report.failChildLength},${report.skipChildLength}</div>
                                        <ul class="pull-right list-inline align-self-end text-muted text-right mb-0">
                                            <li>${100*report.passChildLength/report.childLength}% Pass &nbsp; <span class="badge badge-ring badge-primary ml-2"></span></li>
                                            <li>${100*report.failChildLength/report.childLength}% Failed &nbsp; <span class="badge badge-ring badge-danger ml-2"></span></li>
                                            <li>${100*report.skipChildLength/report.childLength}% Others &nbsp; <span class="badge badge-ring badge-yellow ml-2"></span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="box">
                                    <div class="box-header">
                                        <h3>Step Distribution</h3>
                                    </div>
                                    <div class="box-body kl">
                                        <div class="peity-chart" data-provide="peity" data-type="bar" data-height="130" data-width="90" data-fill="#33cabb,#48b0f7,#fdd501">${report.passGrandChildLength},${report.failGrandChildLength},${report.skipGrandChildLength}</div>
                                        <ul class="pull-right list-inline align-self-end text-muted text-right mb-0">
                                            <li>${100*report.passGrandChildLength/report.childLength}% Pass &nbsp; <span class="badge badge-ring badge-primary ml-2"></span></li>
                                            <li>${100*report.failGrandChildLength/report.childLength}% Failed &nbsp; <span class="badge badge-ring badge-danger ml-2"></span></li>
                                            <li>${100*report.skipGrandChildLength/report.childLength}% Others &nbsp; <span class="badge badge-ring badge-yellow ml-2"></span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            </#if>
                            <div class="col-sm-4">
                                <div class="box">
                                    <div class="box-header">
                                        <h3>Environment</h3>
                                    </div>
                                    <div class="box-body">
                                        <table class="table table-bordered">
                                        	<thead>
                                        		<tr>
                                        			<td>Name</td>
                                        			<td>Value</td>
                                        		</tr>
                                        	</thead>
                                        	<tbody>
                                        		<tr>
                                        			<td></td>
                                        			<td></td>
                                        		</tr>
                                        	</tbody>
                                        </table>
                                    </div>
                                </div>
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
        <script>
            $(".peity-chart").peity("bar", {
            	height: 130,
            	width: 90,
            	fill: [ "#33cabb" ,"#f96868", "#fdd501" ]
            });
        </script>
    </body>
</html>