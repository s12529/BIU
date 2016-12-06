import { AngularModPage } from './app.po';

describe('angular-mod App', function() {
  let page: AngularModPage;

  beforeEach(() => {
    page = new AngularModPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
