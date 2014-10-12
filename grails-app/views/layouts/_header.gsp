<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" fata-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="${createLink(uri: '/')}">
                <img src="${assetPath(src: 'mubs.png')}" class="logo" alt="Mubs">
            </a>
        </div>

        <div class="collapse navbar-collapse">

            <sec:ifNotLoggedIn>
                <ul class="nav navbar-nav navbar-right">
                    <!-- This button triggers a modal -->
                    <li><a href="${createLink(uri: '/login/index')}" class="btn btn-success header-top-padding">Log In</a></li>
                    <li><a href="${createLink(uri: '/user/signup')}" class="btn btn-success header-top-padding">Sign Up</a></li>
                </ul>
            </sec:ifNotLoggedIn>

            <sec:ifLoggedIn>

                <div class="nav navbar-nav">
                    <a href="${createLink(uri: '/blogEntry/newBlogEntry')}" class="btn btn-success header-top-padding">New Blog Entry</a>
                </div>

                <div class="nav navbar-nav navbar-right dropdown header-top-padding">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                        <sec:username/>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(uri: '/user/changePassword')}">Change Password</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(uri: '/logout/index')}">Log Out</a></li>
                    </ul>
                </div>
            </sec:ifLoggedIn>
        </div>	<!--/.nav-collapse -->
    </div>
</div>