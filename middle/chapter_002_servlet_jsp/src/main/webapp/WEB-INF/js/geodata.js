(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Select the node that will be observed for mutations
        const countrySelectorNode = document.getElementById('countryId');
        const stateSelectorNode = document.getElementById('stateId');
        const citySelectorNode = document.getElementById('cityId');

        // Options for the observer (which mutations to observe)
        const config = {attributes: false, childList: true, subtree: false};

        // Callback function to execute when mutations are observed
        const countryCallback = function (mutationsList, observer) {
            for (let mutation of mutationsList) {
                if (mutation.type === 'childList') {
                    console.log('Country selector child node has been added or removed.');
                    if (mutation.addedNodes[0].value === '${user.country}') {
                        console.log(mutation.addedNodes[0].value);
                        $('.countries option[value="' + '${user.country}' + '"]').prop('selected', true);
                        $('.countries').trigger('change');
                        break;
                    }
                }
            }
        };
        const stateCallback = function (mutationsList, observer) {
            for (let mutation of mutationsList) {
                if (mutation.type === 'childList') {
                    console.log('State selector child node has been added or removed.');
                    if (mutation.addedNodes[0].value === '${user.state}') {
                        console.log(mutation.addedNodes[0].value);
                        $('.states option[value="' + '${user.state}' + '"]').prop('selected', true);
                        $('.states').trigger('change');
                        break;
                    }
                }
            }
        };
        const cityCallback = function (mutationsList, observer) {
            for (let mutation of mutationsList) {
                if (mutation.type === 'childList') {
                    console.log('City selector child node has been added or removed.');
                    if (mutation.addedNodes[0].value === '${user.city}') {
                        console.log(mutation.addedNodes[0].value);
                        $('.cities option[value="' + '${user.city}' + '"]').prop('selected', true);
                        break;
                    }
                }
            }
        };

        // Create an observer instance linked to the callback function
        const countryObserver = new MutationObserver(countryCallback);
        const stateObserver = new MutationObserver(stateCallback);
        const cityObserver = new MutationObserver(cityCallback);

        // Start observing the target node for configured mutations
        countryObserver.observe(countrySelectorNode, config);
        stateObserver.observe(stateSelectorNode, config);
        cityObserver.observe(citySelectorNode, config);

        // Later, you can stop observing
        // countryObserver.disconnect();
        // stateObserver.disconnect();
        // cityObserver.disconnect();
    }, false);
})();