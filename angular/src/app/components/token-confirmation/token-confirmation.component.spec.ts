import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TokenConfirmationComponent } from './token-confirmation.component';

describe('TokenConfirmationComponent', () => {
  let component: TokenConfirmationComponent;
  let fixture: ComponentFixture<TokenConfirmationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TokenConfirmationComponent]
    });
    fixture = TestBed.createComponent(TokenConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
