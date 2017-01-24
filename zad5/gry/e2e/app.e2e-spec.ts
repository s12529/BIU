import { RybkiPage } from './app.po';

describe('rybki App', function() {
  let page: RybkiPage;

  beforeEach(() => {
    page = new RybkiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
