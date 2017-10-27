<!DOCTYPE html>
<html lang="en" ng-app="Klov">
    <#include 'partials/head.ftl'>
    <body>
        <div class="app" id="app" ng-controller="UserController">
            <div class="padding">
                <div class="navbar">
                    <div class="pull-center">
                        <!-- brand -->
                        <a href="/" class="navbar-brand">
                        <span class="hidden-folded inline">klov</span>
                        </a>
                        <!-- / brand -->
                    </div>
                </div>
            </div>
            <div class="b-t">
                <div class="center-block w-xxl w-auto-xs p-y-md text-center">
                    <div class="p-a-md">
                        <form name="form" action="/auth/authenticate" method="post">
                            <!-- action="/" -->
                            <#if message??>
                            <div class="box-color red-A200 pos-rlt">
                                <span class="arrow bottom b-danger"></span>
                                <div class="box-body">${message}</div>
                            </div>
                            </#if>
                            <div class="form-group">
                                <input type="username" name="username" class="form-control" placeholder="username" required>
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control" placeholder="password" required>
                            </div>
                            <div class="m-b-md">        
                                <label class="md-check">
                                <input type="checkbox"><i class="primary"></i> Keep me signed in
                                </label>
                            </div>
                            <button type="submit" class="btn btn-lg black p-x-lg">Sign in</button>
                        </form>
                        <div class="m-y">
                            <a href="forgot-password.html" class="_600">Forgot password?</a>
                        </div>
                        <div>
                            Do not have an account? 
                            <a href="signup.html" class="text-primary _600">Sign up</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include 'partials/scripts.ftl'>
        <#include 'partials/angular.ftl'>
    </body>
</html>