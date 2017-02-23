# BaseMVP
Android implementation of MVP (Model-View-Presenter).

The library contains base classes that you can use to implement an MVP architecture into you application.  
It takes care of injecting the Presenter into your views and attaching/detaching the view from the Presenter.

Everything else is up to you!

## Getting started

    dependencies {
        compile 'com.julienarzul:basemvp:1.0.0'
    }

## How to use
### Description
Each of your screen should be separated into four classes :
* The view contract
* The presenter contract
* The view that extends MvpActivty or MvpFragment and implements the view contract
* The presenter that extends BasePresenter and implements the presenter contract

For a better readability, you can regroup the two interfaces in a common file.

### Example
Below is an example of the three files that you will need for an EventList screen:

* View and presenter Contracts:
```java
interface EventListContract {

    interface View extends MvpContract.View {
        ...
    }

    interface Presenter extends MvpContract.Presenter<View> {
        ...
    }
    
}
```

* View Class:
```java
public class EventListActivity extends MvpActivity<EventListContract.Presenter> implements EventListContract.View {
   ...
}
```

* Presenter Class:
```java
class EventListPresenter extends BasePresenter<EventListContract.View> implements EventListContract.Presenter {
    ...
}
```


## Sample
The repository contains a basic example on how to use the MVP classes with both an Activity and a Fragment.

## Inpiration
The MVP implementation and the sample architecture are mostly inspired by:
* [The Mosby library](https://github.com/sockeqwe/mosby) 
* The Clean Architecture patterns ([here is a good article](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/))

## Contributing
Any contributions is welcome through Pull Requests. Please take the time to clearly explain the feature you wish to add or the bug you're trying to fix.

Don't hesitate to raise an issue before opening a pull request so that we can discuss it before-hand.

## License

    Copyright 2017 Julien Arzul

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
