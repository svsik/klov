<!DOCTYPE html>
<html lang="en" ng-app="Klov">
    <#include 'partials/head.ftl'>
    <style type="text/css">
        .select2, .select2-container--default .select2-selection--single {
        	background: #283593 !important;
        }
        .select2-container--default .select2-selection--single .select2-selection__rendered {
        	color: #fff !important;
        	line-height: 52px;
        }
        .select2-container .select2-selection--single {
        	height: 52px;
        }
        .select2-container--default .select2-selection--single .select2-selection__arrow {
        	height: 52px;
        }
    </style>
    <body>
        <div class="app" id="app">
        	<div id="content" class="app-content box-shadow-z2 bg pjax-container" role="main">
                <div class="app-header indigo-800 b-b">
                    <div class="navbar" data-pjax>
                        <a data-toggle="modal" data-target="#aside" class="navbar-item pull-left hidden-lg-up p-r m-a-0">
                        <i class="ion-navicon"></i>
                        </a>
                        <div class="navbar-item pull-left h6" id="pageTitle">klov.</div>
                    </div>
                </div>
            </div>
            <div class="indigo-800 h-v row-col">
                <div class="row-cell v-m text-center animated fadeIn">
                    <form id="form" action="/assignProjectById" method="post">
	                    <div class="form-group">
	                        <select id="single" name="id" class="form-control select2 indigo-800" style="width:250px;padding:20px;margin:0 auto;" data-ui-jp="select2" data-ui-options="{theme: 'bootstrap'}">
	                        	<option></option>
	                        	<#list projectList as project>
	                            <option value="${project.id}">${project.name}</option>
	                            </#list>
	                        </select>
	                        <div class="m-t">
		                        <br/>
		                        <a href="#" class="btn btn-outline b-white rounded p-x-md" onclick="form.submit()">Go</a>
		                    </div>
	                    </div>
                    </form>
                </div>
            </div>
            <!-- ############ LAYOUT END-->
        </div>
        <#include 'partials/scripts.ftl'>
        <#include 'partials/angular.ftl'>
		<script>
			$(document).ready(function() {
			    $('#single').select2({
			    	placeholder: "Select your project"
			    });
			});
		</script>
    </body>
</html>